/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.contabilidad;

import framework.aplicacion.TablaGenerica;
import java.util.List;
import javax.ejb.Stateless;
import sistema.aplicacion.Utilitario;

/**
 *
 * @author dfjacome
 */
@Stateless
public class ServicioConfiguracion {

    private final Utilitario utilitario = new Utilitario();

    /**
     * Retorna la cuenta contable de una Persona a partir de un identificador,
     * la busqueda se realiza con recursividad
     *
     * @param identificador
     * @param ide_geper
     * @return
     */
    public String getCuentaPersona(String identificador, String ide_geper) {
        String str_sql = "";
        String str_padre = null;
        String str_cuenta = null;
        //Busca si existe la persona
        str_sql = "select ide_geper,gen_ide_geper from gen_persona where ide_geper=" + ide_geper;
        TablaGenerica tab_identificador = utilitario.consultar(str_sql);
        if (tab_identificador.getTotalFilas() > 0) {
            str_cuenta = buscarCuenta(identificador, ide_geper, null, null, null, null, null);
            str_padre = tab_identificador.getValor(0, "gen_ide_geper");
            if (str_cuenta == null) {
                if (str_padre == null) {
                    return null;
                } else {
                    return getCuentaPersona(identificador, str_padre);
                }

            } else {
                return str_cuenta;
            }
        }
        return null;
    }

    /**
     * Busca la cuenta contable a travez de un identificador y varios criterios
     * de busqueda
     *
     * @param identificador Identificador cuenta contable
     * @param ide_geper Codigo de la Persona
     * @param articulo Codigo articulo
     * @param impuesto Codigo de impuesto
     * @param porcentajeImpuesto Codigo porcentaje Impuesto
     * @param transaccionInventario Codigo de transaccion de Inventario
     * @param transaccionActivo Codigo de transaccion de Activos Fijos
     * @return
     */
    public String buscarCuenta(String identificador, String ide_geper, String articulo, String impuesto, String porcentajeImpuesto, String transaccionInventario, String transaccionActivo) {
        String str_sql = "";
        String ide_identificador = getCodigoIdentificador(identificador);
        if (ide_identificador != null) {
            str_sql = "select cn_d.ide_cndpc "
                    + "from con_vig_conf_asie cn_v, con_det_conf_asie cn_d "
                    + "where cn_v.ide_cnvca = cn_d.ide_cnvca "
                    + "and cn_v.ide_cncca =" + ide_identificador + " "
                    + "and cn_v.estado_cnvca=true ";
            if (ide_geper != null) {
                str_sql += "and ide_geper=" + ide_geper + " ";
            }
            if (articulo != null) {
                str_sql += "and ide_inarti=" + articulo + " ";
            }
            if (impuesto != null) {
                str_sql += "and ide_cncim=" + impuesto + " ";
            }
            if (porcentajeImpuesto != null) {
                str_sql += "and ide_cnpim=" + porcentajeImpuesto + " ";
            }
            if (transaccionInventario != null) {
                str_sql += "and ide_intti=" + transaccionInventario + " ";
            }
            if (transaccionActivo != null) {
                str_sql += "and ide_acttr=" + transaccionActivo + " ";
            }

            List lis_configura = utilitario.getConexion().consultar(str_sql);
            if (lis_configura != null && !lis_configura.isEmpty()) {
                //  return ltab_identificador.obtener_texto(0, "ide_cndpc");
                return lis_configura.get(0) + "";
            }
        } else {
            System.out.println("no existe identificador");
            utilitario.agregarMensajeError("No existe el identificador : " + identificador, " No existe un identificador con ese nombre para obtener la configuración para el asiento contable");
        }
        return null;
    }

    /**
     * Retorna el codigo de un identificador
     *
     * @param identificador
     * @return
     */
    public String getCodigoIdentificador(String identificador) {
        List lis_identificador = utilitario.getConexion().consultar("SELECT IDE_CNCCA FROM con_cab_conf_asie WHERE UPPER(nombre_cncca) LIKE '" + identificador.toUpperCase() + "'");
        if (lis_identificador != null && !lis_identificador.isEmpty()) {
            return lis_identificador.get(0) + "";
        }
        return null;
    }

    /**
     * Retorna el código de vigencia de la configuracion
     * @param identificador
     * @return 
     */
    public String getCodigoVigenciaIdentificador(String identificador) {
        List lis_identificador = utilitario.getConexion().consultar("SELECT ide_cnvca FROM con_vig_conf_asie WHERE ide_cncca =" + getCodigoIdentificador(identificador) + " and estado_cnvca=true");
        if (lis_identificador != null && !lis_identificador.isEmpty()) {
            return lis_identificador.get(0) + "";
        }
        return null;
    }

}