/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.activos;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import servicios.ServicioBase;

/**
 *
 * @author DIEGOFERNANDOJACOMEG
 */
@Stateless

public class ServicioActivosFijos extends ServicioBase {

    @PostConstruct
    public void init() {
        //Recupera todos los parametros que se van a ocupar en el EJB
        //parametros = utilitario.getVariables("p_cxc_estado_factura_normal", "p_cxc_tipo_trans_factura", "p_cxc_tipo_trans_pago");
    }

    /**
     * Retorna la sentencia SQL con el Listado de Activos Fijos de la empresa
     *
     * @return
     */
    public String getSqlListaActivosFijos() {
        return "select ide_acafi,act_ide_acafi,(case when act_ide_acafi is null then ide_acafi||' 1' else act_ide_acafi||' '||sec_masivo_acafi  end) as SECUENCIAL,nombre_accla AS CLASE,nombre_inarti as TIPO_ACTIVO,cantidad_acafi as CANTIDAD,codigo_barras_acafi,nombre_aceaf as ESTADO,nom_geper,nombre_acuba as AREA_UBICACION,fecha_compra_acafi \n"
                + " ,anos_uso_acafi,vida_util_acafi,valor_compra_acafi,valor_comercial_acafi,valor_remate_acafi,nombre_gecas as CASA,nombre_geobr as OBRA,observacion_acafi\n"
                + "from act_activo_fijo a \n"
                + "left join act_estado_activo_fijo b on a.ide_aceaf=b.ide_aceaf\n"
                + "left join gen_persona c on a.ide_geper=c.ide_geper\n"
                + "left join inv_articulo arti on arti.ide_inarti = a.ide_inarti "
                + "left join act_ubicacion_activo d on a.ide_acuba=d.ide_acuba\n"
                + "left join gen_casa e on a.ide_gecas=e.ide_gecas\n"
                + "left join gen_obra f on a.ide_geobr=f.ide_geobr\n"
                + "left join act_clase_activo g on a.ide_accla=g.ide_accla\n"
                + "where a.ide_empr=" + utilitario.getVariable("IDE_EMPR") + " and ide_accls=1 "//ACTIVO FIJO
                + "order by nombre_gecas,nombre_geobr,nombre_accla,nombre_inarti,ide_acafi";
    }

    public String getSqlListaActivosFijosDarBaja() {
        return "select ide_acafi,act_ide_acafi,(case when act_ide_acafi is null then ide_acafi||' 1' else act_ide_acafi||' '||sec_masivo_acafi  end) as SECUENCIAL,nombre_accla AS CLASE,nombre_inarti as TIPO_ACTIVO,cantidad_acafi as CANTIDAD,codigo_barras_acafi,nombre_aceaf as ESTADO,nom_geper,nombre_acuba as AREA_UBICACION,fecha_compra_acafi \n"
                + " ,anos_uso_acafi,vida_util_acafi,valor_compra_acafi,valor_comercial_acafi,valor_remate_acafi,nombre_gecas as CASA,nombre_geobr as OBRA,observacion_acafi\n"
                + "from act_activo_fijo a \n"
                + "left join act_estado_activo_fijo b on a.ide_aceaf=b.ide_aceaf\n"
                + "left join gen_persona c on a.ide_geper=c.ide_geper\n"
                + "left join inv_articulo arti on arti.ide_inarti = a.ide_inarti "
                + "left join act_ubicacion_activo d on a.ide_acuba=d.ide_acuba\n"
                + "left join gen_casa e on a.ide_gecas=e.ide_gecas\n"
                + "left join gen_obra f on a.ide_geobr=f.ide_geobr\n"
                + "left join act_clase_activo g on a.ide_accla=g.ide_accla\n"
                + "where a.ide_empr=" + utilitario.getVariable("IDE_EMPR") + " and ide_accls=1 "//ACTIVO FIJO
                + " and a.ide_aceaf!=" + utilitario.getVariable("p_act_estado_dado_de_baja") + " "
                + "order by nombre_gecas,nombre_geobr,nombre_accla,nombre_inarti,ide_acafi";
    }

    public String getSqlListaBienesControl() {
        return "select ide_acafi,act_ide_acafi,(case when act_ide_acafi is null then ide_acafi||' 1' else act_ide_acafi||' '||sec_masivo_acafi  end) as SECUENCIAL,nombre_accla AS CLASE,nombre_inarti as TIPO_ACTIVO,cantidad_acafi as CANTIDAD,codigo_barras_acafi,nombre_aceaf as ESTADO,nom_geper,nombre_acuba as AREA_UBICACION,fecha_compra_acafi \n"
                + " ,anos_uso_acafi,vida_util_acafi,valor_compra_acafi,valor_comercial_acafi,valor_remate_acafi,nombre_gecas as CASA,nombre_geobr as OBRA,observacion_acafi\n"
                + "from act_activo_fijo a \n"
                + "left join act_estado_activo_fijo b on a.ide_aceaf=b.ide_aceaf\n"
                + "left join gen_persona c on a.ide_geper=c.ide_geper\n"
                + "left join inv_articulo arti on arti.ide_inarti = a.ide_inarti "
                + "left join act_ubicacion_activo d on a.ide_acuba=d.ide_acuba\n"
                + "left join gen_casa e on a.ide_gecas=e.ide_gecas\n"
                + "left join gen_obra f on a.ide_geobr=f.ide_geobr\n"
                + "left join act_clase_activo g on a.ide_accla=g.ide_accla\n"
                + "where a.ide_empr=" + utilitario.getVariable("IDE_EMPR") + " and ide_accls=2 "//BIENES DE CONTROL
                + "order by nombre_gecas,nombre_geobr,nombre_accla,nombre_inarti,ide_acafi";
    }

    public String getSqlActivoFijo(String ide_acafi) {
        return "select ide_acafi,act_ide_acafi,(case when act_ide_acafi is null then ide_acafi||' 1' else act_ide_acafi||' '||sec_masivo_acafi  end) as SECUENCIAL,nombre_accla AS CLASE,nombre_inarti as TIPO_ACTIVO,cantidad_acafi as CANTIDAD,codigo_barras_acafi,nombre_aceaf as ESTADO,nom_geper,nombre_acuba as AREA_UBICACION,fecha_compra_acafi \n"
                + " ,anos_uso_acafi,vida_util_acafi,valor_compra_acafi,valor_comercial_acafi,valor_remate_acafi,nombre_gecas as CASA,nombre_geobr as OBRA,observacion_acafi\n"
                + "from act_activo_fijo a \n"
                + "left join act_estado_activo_fijo b on a.ide_aceaf=b.ide_aceaf\n"
                + "left join gen_persona c on a.ide_geper=c.ide_geper\n"
                + "left join inv_articulo arti on arti.ide_inarti = a.ide_inarti "
                + "left join act_ubicacion_activo d on a.ide_acuba=d.ide_acuba\n"
                + "left join gen_casa e on a.ide_gecas=e.ide_gecas\n"
                + "left join gen_obra f on a.ide_geobr=f.ide_geobr\n"
                + "left join act_clase_activo g on a.ide_accla=g.ide_accla\n"
                + "where a.ide_empr=" + utilitario.getVariable("IDE_EMPR") + " "
                + " and ide_acafi=" + ide_acafi + " "
                + "order by nombre_gecas,nombre_geobr,nombre_accla,nombre_inarti,ide_acafi";
    }

    /**
     * Retorna el historial de Asignación de un Activo Fijo
     *
     * @param ide_acafi
     * @return
     */
    public String getSqlHistoriaAsignacionActivo(String ide_acafi) {
        return "select ide_acaaf,ide_acafi,nom_geper,fecha_acaaf,observacion_acaaf,nom_usua from act_asignacion_activo a "
                + "left join gen_persona per on per.ide_geper=a.ide_geper "
                + "left join sis_usuario u on u.ide_usua=a.ide_usua "
                + "where a.ide_acafi=" + ide_acafi + " "
                + "order by a.fecha_acaaf DESC";
    }

    /**
     * Retorna el Transacciones de un Activo Fijo
     *
     * @param ide_acafi
     * @return
     */
    public String getSqlTransaccionesActivo(String ide_acafi) {
        return "select ide_actra,nombre_acttr,fecha_actra,acumulado_actra,valor_actra,\n"
                + "valor_activo_actra,cantidad_actra,observacion_actra,nom_usua\n"
                + "from act_transaccion a\n"
                + "inner join act_tipo_transaccion b on a.ide_acttr=b.ide_acttr\n"
                + "inner join sis_usuario c on a.ide_usua=c.ide_usua\n"
                + "where a.ide_acafi=" + ide_acafi + " "
                + "order by fecha_actra desc";
    }

    /**
     * Retorna todos los activos Datdos de Baja
     *
     * @return
     */
    public String getSqlActivosDadosdeBaja() {
        return "select af.ide_acafi as baja_ide_acafi,nombre_inarti as grupo,nombre_acafi as descripcion,serie_acafi as serie,modelo_acafi as modelo,nombre_acuba as ubicacion,nom_geper as custodio_actual from act_activo_fijo af "
                + "left join gen_persona per on per.ide_geper = af.ide_geper "
                + "left join inv_articulo arti on arti.ide_inarti = af.ide_inarti "
                + "left join act_ubicacion_activo acubi on acubi.ide_acuba=af.ide_acuba "
                + "where ide_aceaf =" + utilitario.getVariable("p_act_estado_dado_de_baja") + "";
    }

    /**
     * Retorna los activos Fijos de un custodio
     *
     * @param ide_geper
     * @return
     */
    public String getSqlActivosAsignados(String ide_geper) {
        return "select af.ide_acafi as baja_ide_acafi,nombre_inarti as grupo,nombre_acafi as descripcion,serie_acafi as serie,modelo_acafi as modelo,nombre_acuba as ubicacion,nom_geper as custodio_actual from act_activo_fijo af "
                + "left join gen_persona per on per.ide_geper = af.ide_geper "
                + "left join inv_articulo arti on arti.ide_inarti = af.ide_inarti "
                + "left join act_ubicacion_activo acubi on acubi.ide_acuba=af.ide_acuba "
                + "where af.ide_geper =" + ide_geper + "";
    }

    public int getSecuencialActaConstatacion() {
        int maximo = 0;
        List lis_max = utilitario.getConexion().consultar("SELECT max(secuencial_acact)+1  from act_acta_constata where "
                + " ide_empr=" + utilitario.getVariable("ide_empr"));
        if (lis_max.get(0) != null) {
            try {
                maximo = ((Integer.parseInt(lis_max.get(0).toString())));
            } catch (Exception e) {
                maximo = 1;
            }
        } else {
            maximo = 1;
        }
        return maximo;
    }
//    public String getSecuencial(String ide_gecas, String ide_geobr, String ide_acuba, String ide_accla, String ide_inarti) {
//        if (ide_gecas == null || ide_gecas.isEmpty()) {
//            return "";
//        }
//        if (ide_geobr == null || ide_geobr.isEmpty()) {
//            return "";
//        }
//        if (ide_acuba == null || ide_acuba.isEmpty()) {
//            return "";
//        }
//        if (ide_accla == null || ide_accla.isEmpty()) {
//            return "";
//        }
//        if (ide_inarti == null || ide_inarti.isEmpty()) {
//            return "";
//        }
//        int maximo = 0;
//        List lis_max = utilitario.getConexion().consultar("SELECT max(CAST(coalesce(secuencial_acafi, '0') AS BIGINT))+1  from act_activo_fijo where "
//                + " ide_gecas=" + ide_gecas
//                + " and ide_geobr=" + ide_geobr
//                + " and ide_accla=" + ide_accla
//                + " and ide_inarti=" + ide_inarti
//                + " and ide_acuba=" + ide_acuba
//                + " and ide_empr=" + utilitario.getVariable("ide_empr"));
//        if (lis_max.get(0) != null) {
//            try {
//                maximo = ((Integer.parseInt(lis_max.get(0).toString())) + 1);
//            } catch (Exception e) {
//            }
//        } else {
//            maximo = 1;
//        }
//        return String.format("%04d", new Object[]{maximo});
//    }

    public String getSqlActivosHijoMasivo(String ide_acafi) {
        return "select ide_acafi,nombre_inarti as TIPO_ACTIVO,sec_masivo_acafi AS SECUENCIAL,codigo_barras_acafi AS CODIGO_BARRAS,nombre_aceaf AS ESTADO from act_activo_fijo a "
                + "left JOIN inv_articulo b on  a.ide_inarti=b.ide_inarti\n"
                + "left join act_estado_activo_fijo c  on a.ide_aceaf=c.ide_aceaf\n"
                + "where act_ide_acafi=" + ide_acafi + "\n"
                + "order by sec_masivo_acafi";
    }

    public String getSqlListaActivosFijos(String ide_acafi) {
        return "select ide_acafi,act_ide_acafi,nombre_accla AS CLASE,nombre_inarti as TIPO_ACTIVO,cantidad_acafi as CANTIDAD,codigo_barras_acafi,nombre_aceaf as ESTADO,nombre_acuba as AREA_UBICACION,fecha_compra_acafi \n"
                + " ,anos_uso_acafi,vida_util_acafi,valor_compra_acafi,valor_comercial_acafi,valor_remate_acafi,nombre_gecas as CASA,nombre_geobr as OBRA,observacion_acafi\n"
                + "from act_activo_fijo a \n"
                + "left join act_estado_activo_fijo b on a.ide_aceaf=b.ide_aceaf\n"
                + "left join inv_articulo arti on arti.ide_inarti = a.ide_inarti "
                + "left join act_ubicacion_activo d on a.ide_acuba=d.ide_acuba\n"
                + "left join gen_casa e on a.ide_gecas=e.ide_gecas\n"
                + "left join gen_obra f on a.ide_geobr=f.ide_geobr\n"
                + "left join act_clase_activo g on a.ide_accla=g.ide_accla\n"
                + "where a.ide_acafi in ( " + ide_acafi + ") "
                + "order by nombre_gecas,nombre_geobr,nombre_accla,nombre_inarti,ide_acafi";
    }

    public String getSqlListaActivosFijosSinCustodio(boolean bienesControl) {
        String cond = "";
        if (bienesControl) {
            cond = " and ide_accls=2 ";
        } else {
            cond = " and ide_accls!=2 ";
        }
        return "select ide_acafi,act_ide_acafi,nombre_accla AS CLASE,nombre_inarti as TIPO_ACTIVO,cantidad_acafi as CANTIDAD,codigo_barras_acafi,nombre_aceaf as ESTADO,nombre_acuba as AREA_UBICACION,fecha_compra_acafi \n"
                + " ,anos_uso_acafi,vida_util_acafi,valor_compra_acafi,valor_comercial_acafi,valor_remate_acafi,nombre_gecas as CASA,nombre_geobr as OBRA,observacion_acafi\n"
                + "from act_activo_fijo a \n"
                + "left join act_estado_activo_fijo b on a.ide_aceaf=b.ide_aceaf\n"
                + "left join inv_articulo arti on arti.ide_inarti = a.ide_inarti "
                + "left join act_ubicacion_activo d on a.ide_acuba=d.ide_acuba\n"
                + "left join gen_casa e on a.ide_gecas=e.ide_gecas\n"
                + "left join gen_obra f on a.ide_geobr=f.ide_geobr\n"
                + "left join act_clase_activo g on a.ide_accla=g.ide_accla\n"
                + "where a.ide_empr=" + utilitario.getVariable("IDE_EMPR") + " and a.ide_geper is null and cantidad_acafi=1"
                + cond + " "
                + "order by nombre_gecas,nombre_geobr,nombre_accla,nombre_inarti,ide_acafi";
    }

    public String getSqlListaActivosFijosReasignar(boolean bienesControl) {
        String cond = "";
        if (bienesControl) {
            cond = " and ide_accls=2 ";
        } else {
            cond = " and ide_accls!=2 ";
        }
        return "select ide_acafi,act_ide_acafi,nombre_accla AS CLASE,nombre_inarti as TIPO_ACTIVO,cantidad_acafi as CANTIDAD,codigo_barras_acafi,nombre_aceaf as ESTADO,nombre_acuba as AREA_UBICACION,fecha_compra_acafi \n"
                + " ,anos_uso_acafi,vida_util_acafi,valor_compra_acafi,valor_comercial_acafi,valor_remate_acafi,nombre_gecas as CASA,nombre_geobr as OBRA,observacion_acafi\n"
                + "from act_activo_fijo a \n"
                + "left join act_estado_activo_fijo b on a.ide_aceaf=b.ide_aceaf\n"
                + "left join inv_articulo arti on arti.ide_inarti = a.ide_inarti "
                + "left join act_ubicacion_activo d on a.ide_acuba=d.ide_acuba\n"
                + "left join gen_casa e on a.ide_gecas=e.ide_gecas\n"
                + "left join gen_obra f on a.ide_geobr=f.ide_geobr\n"
                + "left join act_clase_activo g on a.ide_accla=g.ide_accla\n"
                + "where a.ide_empr=" + utilitario.getVariable("IDE_EMPR") + " and a.ide_geper is not null and cantidad_acafi=1"
                + cond + " "
                + "order by nombre_gecas,nombre_geobr,nombre_accla,nombre_inarti,ide_acafi";
    }

    public String getSqlListadoActasConstatacion() {
        return "select a.ide_acact,codigo_acact AS CODIGO,fecha_asigna_acact AS FECHA_ASIGNA,nombre_gecas AS CASA,\n"
                + "nombre_geobr AS OBRA, nombre_acuba AS AREA_UBICACION ,nom_geper AS CUSTODIO,\n"
                + "observacion_acact\n"
                + "from act_acta_constata a\n"
                + "inner join act_ubicacion_activo b on a.ide_acuba=b.ide_acuba\n"
                + "inner join gen_casa e on a.ide_gecas=e.ide_gecas\n"
                + "inner join gen_OBRA f on a.ide_geobr=f.ide_geobr\n"
                + "inner join gen_persona g on a.ide_geper=g.ide_geper\n"
                + "order by codigo_acact";
    }

    public boolean isExisteCodBarras(String codigo_barras_acafi) {
        return !utilitario.consultar("select ide_acafi,codigo_barras_acafi from act_activo_fijo where codigo_barras_acafi='" + codigo_barras_acafi + "'").isEmpty();
    }

    public String getSqlConsultaActivos() {
        return "select\n"
                + "a.ide_acact,a.secuencial_acact,a.codigo_acact,a.fecha_asigna_acact,a.nombre_gecas,\n"
                + "a.nombre_geobr,a.nombre_acuba,a.nom_geper,a.observacion_acact,\n"
                + "b.observacion_acaaf,b.fecha_acaaf,\n"
                + "c.nombre_aceaf,c.nombre_geubi,c.nombre_inarti,c.nom_geper,c.cantidad_acafi,c.fecha_acafi,\n"
                + "c.vida_util_acafi,c.valor_compra_acafi,c.deprecia_acafi,c.recidual_acafi,c.serie_acafi,c.observacion_acafi,c.numero_factu_acafi,\n"
                + "c.fecha_compra_acafi,c.ano_actual_acafi,c.anos_uso_acafi,c.valor_comercial_acafi,c.valor_remate_acafi,c.modelo_acafi,\n"
                + "c.nombre_invmar,c.nombre_acuba,c.codigo_barras_acafi,c.nombre_gecas,c.nombre_geobr,c.nombre_accla,\n"
                + "c.act_ide_acafi,c.sec_masivo_acafi,c.cod_anterior_acafi,c.nombre_actac,c.nombre_accls\n"
                + "from (select a.ide_acact,a.secuencial_acact,a.codigo_acact,a.fecha_asigna_acact,b.nombre_gecas,c.nombre_geobr,\n"
                + "d.nombre_acuba,e.nom_geper,a.observacion_acact from act_acta_constata as a\n"
                + "left join gen_casa as b on b.ide_gecas=a.ide_gecas\n"
                + "left join gen_obra as c on c.ide_geobr=a.ide_geobr\n"
                + "left join act_ubicacion_activo as d on d.ide_acuba=a.ide_acuba\n"
                + "left join gen_persona as e on e.ide_geper=a.ide_geper)as a\n"
                + "left join act_asignacion_activo as b on b.ide_acact=a.ide_acact\n"
                + "left join (select a.ide_acafi,b.nombre_aceaf,c.nombre_geubi,d.nombre_inarti,e.nom_geper,\n"
                + "a.cantidad_acafi,a.fecha_acafi,a.vida_util_acafi,a.valor_compra_acafi,case when a.deprecia_acafi is true then 'SI' else 'NO' end as deprecia_acafi,\n"
                + "a.recidual_acafi,a.serie_acafi,observacion_acafi,a.numero_factu_acafi,a.fecha_compra_acafi,a.ano_actual_acafi,\n"
                + "a.anos_uso_acafi,a.valor_comercial_acafi,a.valor_remate_acafi,a.modelo_acafi,f.nombre_invmar,g.nombre_acuba,\n"
                + "a.codigo_barras_acafi,h.nombre_gecas,i.nombre_geobr,j.nombre_accla,\n"
                + "a.act_ide_acafi,a.sec_masivo_acafi,a.cod_anterior_acafi,k.nombre_actac,l.nombre_accls\n"
                + "from act_activo_fijo as a\n"
                + "left join act_estado_activo_fijo as b on b.ide_aceaf=a.ide_aceaf\n"
                + "left join gen_ubicacion as c on c.ide_geubi=a.ide_geubi\n"
                + "left join inv_articulo as d on d.ide_inarti=a.ide_inarti\n"
                + "left join gen_persona as e on e.ide_geper=a.ide_geper\n"
                + "left join inv_marca as f on f.ide_inmar=a.ide_inmar\n"
                + "left join act_ubicacion_activo as g on g.ide_acuba=a.ide_acuba\n"
                + "left join gen_casa as h on h.ide_gecas=a.ide_gecas\n"
                + "left join gen_obra as i on i.ide_geobr=a.ide_geobr\n"
                + "left join act_clase_activo as j on j.ide_accla=a.ide_accla\n"
                + "left join act_tipo_adquisicion as k on k.ide_actac=a.ide_actac\n"
                + "left join act_clasificacion as l on l.ide_accls=a.ide_accls)as c on c.ide_acafi=b.ide_acafi\n"
                + "order by a.secuencial_acact,a.codigo_acact,a.fecha_asigna_acact";
    }

}
