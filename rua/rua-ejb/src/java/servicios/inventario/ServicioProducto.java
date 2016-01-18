/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.inventario;

import framework.aplicacion.TablaGenerica;
import framework.componentes.Tabla;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author dfjacome
 */
@Stateless
public class ServicioProducto {

    private final Utilitario utilitario = new Utilitario();

    /**
     * Retorna la sentencia SQL para obtener los Productos de una empresa, para
     * ser utilizada en Combos, Autocompletar
     *
     * @return
     */
    public String getSqlProductosCombo() {
        return "SELECT ide_inarti,nombre_inarti from inv_articulo arti "
                + "where arti.ide_empr=" + utilitario.getVariable("ide_empr") + "";
    }

    /**
     * Retorna la sentencia SQL para obtener las Bodegas de una empresa, para
     * ser utilizada en Combos, Autocompletar
     *
     * @return
     */
    public String getSqlBodegasCombo() {
        return "select ide_inbod,nombre_inbod from inv_bodega where nivel_inbod='HIJO' and ide_empr=" + utilitario.getVariable("ide_empr");
    }

    /**
     * Asigna las configuraciones de un Producto
     *
     * @param tabla
     */
    public void configurarTablaProducto(Tabla tabla) {
        tabla.setTabla("inv_articulo", "ide_inarti", -1);
        tabla.getColumna("nivel_inarti").setCombo(utilitario.getListaNiveles());
        tabla.getColumna("ide_infab").setCombo("inv_fabricante", "ide_infab", "nombre_infab", "");
        tabla.getColumna("ide_inmar").setCombo("inv_marca", "ide_inmar", "nombre_invmar", "");
        tabla.getColumna("ide_inuni").setCombo("inv_unidad", "ide_inuni", "nombre_inuni", "");
        tabla.getColumna("ide_intpr").setCombo("inv_tipo_producto", "ide_intpr", "nombre_intpr", "");
        tabla.getColumna("ide_inepr").setCombo("inv_estado_produc", "ide_inepr", "nombre_inepr", "");
        tabla.getColumna("nivel_inarti").setValorDefecto("HIJO");
        tabla.getColumna("hace_kardex_inarti").setValorDefecto("true");
        tabla.getColumna("es_combo_inarti").setValorDefecto("false");
        List lista = new ArrayList();
        Object fila1[] = {
            "1", "SI"
        };
        Object fila2[] = {
            "-1", "NO"
        };
        Object fila3[] = {
            "0", "NO  OBJETO"
        };
        lista.add(fila1);
        lista.add(fila2);
        lista.add(fila3);
        tabla.getColumna("iva_inarti").setRadio(lista, "1");
        tabla.getColumna("iva_inarti").setRadioVertical(true);
        tabla.setTipoFormulario(true);
        tabla.getGrid().setColumns(4);
        tabla.getColumna("ide_georg").setCombo("gen_organigrama", "ide_georg", "nombre_georg", "");// cargar un combo de una con el ide, nombre
    }

    /**
     * Retorna la sentencia SQL para obtener las transacciones de un Producto en
     * una Bodega x Sucursal
     *
     * @param ide_inarti Producto
     * @param ide_inbod Bodega
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    public String getSqlTransaccionesProductoBodega(String ide_inarti, String ide_inbod, String fechaInicio, String fechaFin) {
        return "SELECT dci.ide_indci,cci.fecha_trans_incci,tci.signo_intci,nombre_intti,"
                + "cantidad_indci,precio_indci,valor_indci,precio_promedio_indci,"
                + "case when signo_intci = 1 THEN valor_indci  end as INGRESOS,case when signo_intci = -1 THEN valor_indci end as EGRESOS,'' as saldo_cant,'' as saldo_valor "
                + "from inv_det_comp_inve dci "
                + "left join inv_cab_comp_inve cci on cci.ide_incci=dci.ide_incci "
                + "left join inv_tip_tran_inve tti on tti.ide_intti=cci.ide_intti "
                + "left join inv_tip_comp_inve tci on tci.ide_intci=tti.ide_intci "
                + "left join inv_articulo arti on dci.ide_inarti=arti.ide_inarti "
                + "where dci.ide_inarti=" + ide_inarti + " "
                + "and dci.ide_sucu=" + utilitario.getVariable("IDE_SUCU") + " "
                + "and fecha_trans_incci BETWEEN '" + fechaInicio + "'  and '" + fechaFin + "' "
                + "and ide_inepi=" + utilitario.getVariable("p_inv_estado_normal") + " "
                + "and ide_inbod=" + ide_inbod + " "
                + "ORDER BY cci.fecha_trans_incci,dci.ide_indci asc";
    }

    /**
     * Retorna la cantidad inicial de un Producto en una Bodega X SUCURSAL a una
     * fecha determinada
     *
     * @param ide_inarti Producto
     * @param ide_inbod Bodega
     * @param fecha
     * @return
     */
    public double getCantidadInicialProductoBodega(String ide_inarti, String ide_inbod, String fecha) {
        double saldo = 0;
        String sql = "select dci.ide_inarti,sum(cantidad_indci * signo_intci) as cantidad "
                + "from inv_det_comp_inve dci "
                + "left join inv_cab_comp_inve cci on cci.ide_incci=dci.ide_incci "
                + "left join inv_tip_tran_inve tti on tti.ide_intti=cci.ide_intti "
                + "left join inv_tip_comp_inve tci on tci.ide_intci=tti.ide_intci "
                + "where dci.ide_inarti=" + ide_inarti + " "
                + "and fecha_trans_incci <'" + fecha + "' "
                + "and dci.ide_sucu=" + utilitario.getVariable("ide_sucu") + " "
                + "and ide_inbod=" + ide_inbod + " "
                + "and ide_inepi=" + utilitario.getVariable("p_inv_estado_normal") + " "
                + "group by dci.ide_inarti";
        TablaGenerica tab_saldo = utilitario.consultar(sql);
        if (tab_saldo.getTotalFilas() > 0) {
            if (tab_saldo.getValor(0, "cantidad") != null) {
                try {
                    saldo = Double.parseDouble(tab_saldo.getValor(0, "cantidad"));
                } catch (Exception e) {
                }
            }
        }
        return saldo;
    }

    /**
     * Retorna el precio promedio de un Producto
     *
     * @param ide_inarti
     * @param ide_inbod
     * @param fecha
     * @return
     */
    public double getSaldoPromedioInicialProductoBodega(String ide_inarti, String ide_inbod, String fecha) {
        double precio_promedio = 0;
        String sql = "select dci.ide_inarti,fecha_trans_incci,precio_promedio_indci "
                + "from inv_det_comp_inve dci "
                + "left join inv_cab_comp_inve cci on dci.ide_incci=cci.ide_incci "
                + "where dci.ide_inarti=" + ide_inarti + " "
                + "and fecha_trans_incci <'" + fecha + "' "
                + "and dci.ide_sucu=" + utilitario.getVariable("ide_sucu") + " "
                + "and ide_inbod=" + ide_inbod + " "
                + "and ide_inepi=" + utilitario.getVariable("p_inv_estado_normal") + " "
                + "order by fecha_trans_incci desc limit 1";
        TablaGenerica tab_saldo = utilitario.consultar(sql);
        if (tab_saldo.getTotalFilas() > 0) {
            if (tab_saldo.getValor(0, "precio_promedio_indci") != null) {
                try {
                    precio_promedio = Double.parseDouble(tab_saldo.getValor(0, "precio_promedio_indci"));
                } catch (Exception e) {
                }
            }
        }
        return precio_promedio;
    }

}