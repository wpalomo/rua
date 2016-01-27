-----TABLA DE CONFIGURACION DE LAS DIRECCIONES Y CREDENCIALES DE LOS WEB SERVICES
CREATE TABLE public.gen_webservice
(
   ide_gewes smallint NOT NULL, 
   nombre_gewes character varying(150), 
   wsdl_gewes text, 
   usuario_gewes character varying(100), 
   password_gewes character varying(80), 
   tiempomax_gewes integer, 
   CONSTRAINT "PK_GEN_WEBSERVICE" PRIMARY KEY (ide_gewes)
) 
WITH (
  OIDS = FALSE
)
;
COMMENT ON TABLE public.gen_webservice
  IS 'TABLA DE CONFIGURACION DE DIRECCIONES WSDL DE WEB SERVICES ';

-----AUMENTA COLUMNAS A LA TABLA DE SIS_EMPRESA
ALTER TABLE sis_empresa
  ADD COLUMN contribuyenteespecial_empr character varying(20);
ALTER TABLE sis_empresa
  ADD COLUMN obligadocontabilidad_empr character varying(20);
COMMENT ON COLUMN sis_empresa.contribuyenteespecial_empr IS 'NUMERO DE RESOLUCION DE CONTRIBUYENTE ESPECIAL';

-----CREA TABLA DE CLAVES DE CONTINGENCIA
CREATE TABLE public.sri_claves_contingencia 
( 
    ide_srclc         bigint NOT NULL,
    clavecont_srclc   character varying(80), 
    disponible_srclc  boolean, 
   CONSTRAINT "PK_SRI_CLAVES_CONTIGENCIA" PRIMARY KEY (ide_srclc)
)
WITH (
  OIDS = FALSE
)
;


----CREA TABLA ESTADO COMPROBANTE ELECTRONICO
CREATE TABLE public.sri_estado_comprobante ( 
	ide_sresc	smallint NOT NULL, 
	nombre_sresc	varchar(80) NULL, 
        CONSTRAINT "PK_SRI_ESTADO_COMPROBANTE" PRIMARY KEY (ide_sresc)
)
WITH (
  OIDS = FALSE
)
;
INSERT INTO public.sri_estado_comprobante VALUES(1, 'RECIBIDA');
INSERT INTO public.sri_estado_comprobante VALUES(2, 'DEVUELTA');
INSERT INTO public.sri_estado_comprobante VALUES(3, 'AUTORIZADO');
INSERT INTO public.sri_estado_comprobante VALUES(4, 'RECHAZADO');
INSERT INTO public.sri_estado_comprobante VALUES(5, 'CONTINGENCIA');


-----TABLA DE FIRMAS ELECRONICAS
CREATE TABLE public.sri_firma_digital ( 
	ide_srfid                       smallint NOT NULL, 
	ruta_srfid                      text NOT NULL,
	password_srfid                  character varying(80) NOT NULL,
	fecha_ingreso_srfid       	date NOT NULL,
	fecha_caduca_srfid              date ,
	nombre_representante_srfid	character varying(120),
	correo_representante_srfid	character varying(100),
	disponible_srfid                boolean,
        CONSTRAINT "PK_SRI_FIRMA_DIGITAL" PRIMARY KEY (ide_srfid)
)
WITH (
  OIDS = FALSE
)
;
INSERT INTO public.sri_firma_digital VALUES(1, 'firmas/edgar_mesias_tapia_coral.p12', 'Ecuador8', '2015-01-01', '2016-10-31', 'EDGAR TAPIA', 'edgartapia@bnf.fin.ec', 'true')


------TABLA COMPROBANTES ELECTRONICOS
create table public.sri_comprobante ( 
	ide_srcom                       bigint NOT NULL,
	ide_geper                       bigint,
	codigoemisor_srcom              smallint, 
	ide_srclc                       bigint,  --clave contingencia
	ide_srfid                       smallint,--firma digital 
	ide_sresc                       smallint ,--estado comprobante
	ide_cntdo                       bigint , --tipo documento  con_tipo_document
        ide_cccfa                       bigint , -- factura de venta
        ide_cncre                       bigint , -- Retencion 
	tipoemision_srcom               smallint,        
	claveacceso_srcom               character varying(50) not null,
	coddoc_srcom              	character varying(2) not null,
	estab_srcom                     character varying(3) not null,
	ptoemi_srcom                    character varying(3) not null,
	secuencial_srcom             	character varying(9) not null,
	fechaemision_srcom           	date not null,
        autorizacion_srcom              character varying(100),
        fechaautoriza_srcom           	date,
	direstablecimiento_srcom     	character varying(255) null,
	tipoidentificacion_srcom     	character varying(2) not null,
	guiaremision_srcom             	character varying(15) null,
	totalsinimpuestos_srcom        	numeric(14,2) null,
	totaldescuento_srcom           	numeric(14,2) null,
	propina_srcom                  	numeric(14,2) not null,
	importetotal_srcom             	numeric(14,2) not null,
	moneda_srcom                   	character varying(20) null,
	periodofiscal_srcom            	character varying(7) null,
	rise_srcom                     	character varying(40) null,
	coddocmodificado_srcom         	character varying(2) null,
	numdocmodificado_srcom         	character varying(20) null,
	fechaemisiondocsustento_srcom  	date null,
	valormodificacion_srcom        	numeric(14,2) null ,
        CONSTRAINT "PK_SRI_COMPROBANTE" PRIMARY KEY (ide_srcom),
        CONSTRAINT fk_con_retencion_comprobante FOREIGN KEY (ide_cncre)
        REFERENCES con_cabece_retenc (ide_cncre) MATCH SIMPLE
        ON UPDATE RESTRICT ON DELETE RESTRICT,
        CONSTRAINT fk_cxc_factura_comprobante FOREIGN KEY (ide_cccfa)
        REFERENCES cxc_cabece_factura (ide_cccfa) MATCH SIMPLE
        ON UPDATE RESTRICT ON DELETE RESTRICT,
        CONSTRAINT fk_gen_persona_comprobante FOREIGN KEY (ide_geper)
        REFERENCES gen_persona (ide_geper) MATCH SIMPLE
        ON UPDATE RESTRICT ON DELETE RESTRICT,
        CONSTRAINT fk_sri_estado_comprobante FOREIGN KEY (ide_sresc)
        REFERENCES sri_estado_comprobante (ide_sresc) MATCH SIMPLE
        ON UPDATE RESTRICT ON DELETE RESTRICT,
        CONSTRAINT fk_sri_firma_comprobante FOREIGN KEY (ide_srfid)
        REFERENCES sri_firma_digital (ide_srfid) MATCH SIMPLE
        ON UPDATE RESTRICT ON DELETE RESTRICT,
        CONSTRAINT fk_sri_clave_comprobante FOREIGN KEY (ide_srclc)
        REFERENCES sri_claves_contingencia (ide_srclc) MATCH SIMPLE
        ON UPDATE RESTRICT ON DELETE RESTRICT,
        CONSTRAINT fk_con_documento_comprobante FOREIGN KEY (ide_cntdo)
        REFERENCES con_tipo_document (ide_cntdo) MATCH SIMPLE
        ON UPDATE RESTRICT ON DELETE RESTRICT
)
WITH (
  OIDS = FALSE
)
;


