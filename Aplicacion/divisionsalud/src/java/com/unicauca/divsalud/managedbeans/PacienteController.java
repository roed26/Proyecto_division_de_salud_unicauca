package com.unicauca.divsalud.managedbeans;

import com.unicauca.divsalud.entidades.CategoriaAfiliado;
import com.unicauca.divsalud.entidades.Depto;
import com.unicauca.divsalud.entidades.Eps;
import com.unicauca.divsalud.entidades.Escolaridad;
import com.unicauca.divsalud.entidades.EstadoCivil;
import com.unicauca.divsalud.entidades.Estrato;
import com.unicauca.divsalud.entidades.Facultad;
import com.unicauca.divsalud.entidades.Municipio;
import com.unicauca.divsalud.entidades.Paciente;
import com.unicauca.divsalud.entidades.Parentesco;
import com.unicauca.divsalud.entidades.Programas;
import com.unicauca.divsalud.entidades.Raza;
import com.unicauca.divsalud.entidades.TipoAfiliado;
import com.unicauca.divsalud.entidades.TipoIdentificacion;
import com.unicauca.divsalud.entidades.TipoRegimen;
import com.unicauca.divsalud.managedbeans.util.JsfUtil;
import com.unicauca.divsalud.managedbeans.util.JsfUtil.PersistAction;
import com.unicauca.divsalud.sessionbeans.ActualizacionOdoFacade;
import com.unicauca.divsalud.sessionbeans.PacienteFacade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@Named("pacienteController")
@SessionScoped
public class PacienteController implements Serializable {

    @EJB
    private com.unicauca.divsalud.sessionbeans.PacienteFacade ejbFacade;
    @EJB
    private ActualizacionOdoFacade ejbActualizacionOdo;
    private List<Paciente> items = null;
    private Paciente selected;
    private String busquedaPaciente;
    private TipoIdentificacion tipoIdentificacion;
    private Raza raza;
    private Facultad facultad;
    private Programas programa;
    private EstadoCivil estadoCivil;
    private Escolaridad escolaridad;
    private Estrato estrato;
    private CategoriaAfiliado categoriaAfiliado;
    private TipoAfiliado tipoAfiliado;
    private TipoRegimen tipoRegimen;
    private Eps eps;
    private Municipio municipioResidencia;
    private Depto deptoResidencia;
    private Municipio municipioNacimiento;
    private Depto deptoNacimiento;
    private Parentesco parentesco;

    public static String PASAPORTE = "PASAPORTE";
    private int tipoListar;
    private String menor_sin_identificacion;
    private String adulto_sin_identificacion;

    private DataModel dataModel;
    private DataModel dataModelPacientesHistoria;
    private DataModel dataModelPacientesSinHistoria;
    private String tipo_identificacion;
    private boolean facultad_seleccionada;
    private boolean ver_paciente;

    public PacienteController() {
        facultad_seleccionada = false;
        ver_paciente = false;
        tipoIdentificacion = new TipoIdentificacion();
        tipoIdentificacion.setId("");
        tipoIdentificacion.setNombre("");
        menor_sin_identificacion = "MENOR SIN IDENTIFICACION";
        this.tipoListarPacientes(1);
    }

    @PostConstruct
    public void init() {
        cargarPacientes();
        selected = new Paciente();
        selected.setSexo('F');
        selected.setEstado("1");
        selected.setZonaResidencia('U');
        this.tipoIdentificacion = new TipoIdentificacion();
        this.raza = new Raza();
        facultad = new Facultad();
        programa = new Programas();
        estadoCivil = new EstadoCivil();
        escolaridad = new Escolaridad();
        estrato = new Estrato();
        categoriaAfiliado = new CategoriaAfiliado();
        tipoAfiliado = new TipoAfiliado();
        tipoRegimen = new TipoRegimen();
        eps = new Eps();
        municipioResidencia = new Municipio();
        deptoResidencia = new Depto();
        municipioNacimiento = new Municipio();
        deptoNacimiento = new Depto();
        parentesco = new Parentesco();
        menor_sin_identificacion = "MENOR SIN IDENTIFICACION";
        this.tipoListarPacientes(1);
        facultad_seleccionada = false;
        ver_paciente = false;
    }

    public Paciente getSelected() {
        return selected;
    }

    public void setSelected(Paciente selected) {
        this.selected = selected;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Programas getPrograma() {
        return programa;
    }

    public void setPrograma(Programas programa) {
        this.programa = programa;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Escolaridad getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(Escolaridad escolaridad) {
        this.escolaridad = escolaridad;
    }

    public Estrato getEstrato() {
        return estrato;
    }

    public void setEstrato(Estrato estrato) {
        this.estrato = estrato;
    }

    public CategoriaAfiliado getCategoriaAfiliado() {
        return categoriaAfiliado;
    }

    public void setCategoriaAfiliado(CategoriaAfiliado categoriaAfiliado) {
        this.categoriaAfiliado = categoriaAfiliado;
    }

    public TipoAfiliado getTipoAfiliado() {
        return tipoAfiliado;
    }

    public void setTipoAfiliado(TipoAfiliado tipoAfiliado) {
        this.tipoAfiliado = tipoAfiliado;
    }

    public TipoRegimen getTipoRegimen() {
        return tipoRegimen;
    }

    public void setTipoRegimen(TipoRegimen tipoRegimen) {
        this.tipoRegimen = tipoRegimen;
    }

    public Eps getEps() {
        return eps;
    }

    public void setEps(Eps eps) {
        this.eps = eps;
    }

    public String getMenor_sin_identificacion() {
        return menor_sin_identificacion;
    }

    public void setMenor_sin_identificacion(String menor_sin_identificacion) {
        this.menor_sin_identificacion = menor_sin_identificacion;
    }

    public String getAdulto_sin_identificacion() {
        return adulto_sin_identificacion;
    }

    public void setAdulto_sin_identificacion(String adulto_sin_identificacion) {
        this.adulto_sin_identificacion = adulto_sin_identificacion;
    }

    public String getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public boolean isVer_paciente() {
        return ver_paciente;
    }

    public void viendoPaciente() {
        ver_paciente = true;
    }

    public void noViendoPaciente() {
        ver_paciente = false;
    }

    public boolean isFacultad_seleccionada() {
        return facultad_seleccionada;
    }

    public void seleccionar_facultad() {
        facultad_seleccionada = true;
    }

    public void setFacultad_seleccionada(boolean facultad_seleccionada) {
        this.facultad_seleccionada = facultad_seleccionada;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public DataModel getDataModelPacientesHistoria() {
        return dataModelPacientesHistoria;
    }

    public void setDataModelPacientesHistoria(DataModel dataModelPacientesHistoria) {
        this.dataModelPacientesHistoria = dataModelPacientesHistoria;
    }

    public Municipio getMunicipioResidencia() {
        return municipioResidencia;
    }

    public void setMunicipioResidencia(Municipio municipioResidencia) {
        this.municipioResidencia = municipioResidencia;
    }

    public Depto getDeptoResidencia() {
        return deptoResidencia;
    }

    public void setDeptoResidencia(Depto deptoResidencia) {
        this.deptoResidencia = deptoResidencia;
    }

    public Municipio getMunicipioNacimiento() {
        return municipioNacimiento;
    }

    public void setMunicipioNacimiento(Municipio municipioNacimiento) {
        this.municipioNacimiento = municipioNacimiento;
    }

    public Depto getDeptoNacimiento() {
        return deptoNacimiento;
    }

    public void setDeptoNacimiento(Depto deptoNacimiento) {
        this.deptoNacimiento = deptoNacimiento;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PacienteFacade getFacade() {
        return ejbFacade;
    }

    public String getBusquedaPaciente() {
        return busquedaPaciente;
    }

    public void setBusquedaPaciente(String busquedaPaciente) {
        this.busquedaPaciente = busquedaPaciente;
    }

    public Paciente prepareCreate() {
        selected = new Paciente();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PacienteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PacienteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PacienteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Paciente> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    /**
     * REgistra un nuevo paciente utilizando los componentes
     */
    public String registrar() {
        selected.setTipoIdentificacion(tipoIdentificacion);
        selected.setRaza(raza);
        selected.setFacultad(facultad);
        selected.setPrograma(programa);
        selected.setEstadoCivil(estadoCivil);
        selected.setEscolaridad(escolaridad);
        selected.setEstrato(estrato);
        selected.setCategoriaAfiliado(categoriaAfiliado);
        selected.setTipoAfiliado(tipoAfiliado);
        selected.setTipoRegimen(tipoRegimen);
        selected.setEps(eps);
        selected.setMunicipioResidencia(municipioResidencia);
        selected.setDeptoResidencia(deptoResidencia);
        selected.setMunicipioNacimiento(municipioNacimiento);
        selected.setDeptoNacimiento(deptoNacimiento);
        selected.setParentesco(parentesco);
        asignarFechaApertura();
        try {
            ejbFacade.create(selected);
            limpiarCampos();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro de paciente", "El registro del paciente fue exitoso.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "/usuariodelsistema/sexualidad/paciente/list.xhtml";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro de paciente", "El registro del paciente no ha podido llevarse a cabo.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return "./";
        }

    }

    public void registrar_Paciente() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        selected.setTipoIdentificacion(tipoIdentificacion);
        selected.setRaza(raza);
        selected.setFacultad(facultad);
        selected.setPrograma(programa);
        selected.setEstadoCivil(estadoCivil);
        selected.setEscolaridad(escolaridad);
        selected.setEstrato(estrato);
        selected.setCategoriaAfiliado(categoriaAfiliado);
        selected.setTipoAfiliado(tipoAfiliado);
        selected.setTipoRegimen(tipoRegimen);
        selected.setEps(eps);
        selected.setMunicipioResidencia(municipioResidencia);
        selected.setDeptoResidencia(deptoResidencia);
        selected.setMunicipioNacimiento(municipioNacimiento);
        selected.setDeptoNacimiento(deptoNacimiento);
        selected.setParentesco(parentesco);
        asignarFechaApertura();

        try {
            ejbFacade.create(selected);

            limpiarCampos();

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro de paciente", "El registro del paciente fue exitoso.");

            requestContext.execute("PF('RegistroExitoso').show()");

        } catch (Exception e) {

            requestContext.execute("PF('RegistroNoExitoso').show()");
        }

    }

    public List<Paciente> getListaPacientesActivosEvolucion() {
        List<Paciente> lista = new ArrayList<>();

        items = ejbFacade.findAll();

        for (int i = 0; i < items.size(); i++) {
            boolean existeHistoria = ejbActualizacionOdo.buscarPorPacienteBoolActivo(items.get(i).getId());
            if (existeHistoria == true) {
                lista.add(items.get(i));
            }
        }
        return lista;
    }

    public Date getMaxDate() {
        Calendar cal = Calendar.getInstance();
        return new Date(cal.getTime().getYear(), cal.getTime().getMonth(), cal.getTime().getDate());
    }

    public void buscarPaciente() {
        this.items = getFacade().buscarPacienteEjb(this.busquedaPaciente.toLowerCase());
    }

    public List<Paciente> getListaPacientesActivos() {
        return items;
    }

    public List<Paciente> getListaPacientesActivosAtencion() {
        List<Paciente> lista = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            boolean existeHistoria = ejbActualizacionOdo.buscarPorPacienteBool(items.get(i).getId());
            if (items.get(i).getEstado().equalsIgnoreCase("1") && existeHistoria != true) {
                lista.add(items.get(i));
            }

        }
        return lista;
    }

    public List<Paciente> getListaPacientesActivosApertura() {
        List<Paciente> lista = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            boolean existeHistoria = ejbActualizacionOdo.buscarPorPacienteBool(items.get(i).getId());
            if (items.get(i).getEstado().equalsIgnoreCase("1") && existeHistoria != true) {
                lista.add(items.get(i));
            }

        }
        return lista;
    }

    public List<Paciente> getListaPacientes() {
        List<Paciente> lista = new ArrayList<>();
        //items = ejbFacade.findAll();
        for (int i = 0; i < items.size(); i++) {
            lista.add(items.get(i));
        }
        return lista;
    }

    public List<Paciente> getListaPacientesActivosConHistoria() {
        List<Paciente> lista = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            boolean existeHistoria = ejbActualizacionOdo.buscarPorPacienteBool(items.get(i).getId());
            if (existeHistoria == true) {
                lista.add(items.get(i));
            }

        }
        return lista;
    }

    public void cargarPacientes() {
        items = ejbFacade.findAll();
    }

    public void cargarPaciente(int id) {
        selected = getFacade().find(id);
        seleccionarPacienteEdicion(selected);
    }

    public void cargarPaciente(String id, TipoIdentificacion ti) {
        System.out.println("cargar paciente");
        List<Paciente> pacientes = ejbFacade.findByIdentificacionTipoIdentificacion(id, ti.getId());
        selected = pacientes.get(0);
        seleccionar_CargarPaciente(selected);
        System.out.println("cargado: " + selected.toString());
    }

    //registro de un nuevo paciente 
    public void registrarPaciente() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        selected.setTipoIdentificacion(tipoIdentificacion);
        selected.setRaza(raza);
        selected.setFacultad(facultad);
        selected.setPrograma(programa);
        selected.setEstadoCivil(estadoCivil);
        selected.setEscolaridad(escolaridad);
        selected.setEstrato(estrato);
        selected.setCategoriaAfiliado(categoriaAfiliado);
        selected.setTipoAfiliado(tipoAfiliado);
        selected.setTipoRegimen(tipoRegimen);
        selected.setEps(eps);
        selected.setMunicipioResidencia(municipioResidencia);
        selected.setDeptoResidencia(deptoResidencia);
        selected.setMunicipioNacimiento(municipioNacimiento);
        selected.setDeptoNacimiento(deptoNacimiento);
        selected.setParentesco(parentesco);
        asignarFechaApertura();
        ejbFacade.create(selected);
        requestContext.execute("PF('RegistroExitoso').show()");

    }

    //Nuevos cambios 
    public void seleccionarPacienteEdicion(Paciente paciente) {
        this.selected = paciente;
        this.tipoIdentificacion = paciente.getTipoIdentificacion();
        this.raza = paciente.getRaza();
        facultad = paciente.getFacultad();
        programa = paciente.getPrograma();
        estadoCivil = paciente.getEstadoCivil();
        escolaridad = paciente.getEscolaridad();
        estrato = paciente.getEstrato();
        categoriaAfiliado = paciente.getCategoriaAfiliado();
        tipoAfiliado = paciente.getTipoAfiliado();
        tipoRegimen = paciente.getTipoRegimen();
        eps = paciente.getEps();
        municipioResidencia = paciente.getMunicipioResidencia();
        deptoResidencia = paciente.getDeptoResidencia();
        municipioNacimiento = paciente.getMunicipioNacimiento();
        deptoNacimiento = paciente.getDeptoNacimiento();

    }

    public void editarPaciente() {
        selected.setTipoIdentificacion(tipoIdentificacion);
        selected.setFacultad(facultad);
        selected.setPrograma(programa);
        selected.setEstadoCivil(estadoCivil);
        selected.setEscolaridad(escolaridad);
        selected.setEstrato(estrato);
        selected.setCategoriaAfiliado(categoriaAfiliado);
        selected.setTipoAfiliado(tipoAfiliado);
        selected.setTipoRegimen(tipoRegimen);
        selected.setEps(eps);
        selected.setMunicipioResidencia(municipioResidencia);
        selected.setDeptoResidencia(deptoResidencia);
        selected.setMunicipioNacimiento(municipioNacimiento);
        selected.setDeptoNacimiento(deptoNacimiento);
        selected.setParentesco(parentesco);
        ejbFacade.edit(selected);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('ActualizacionExitosa').show()");

    }

    public void limpiarCampos() {
        selected = new Paciente();
        selected.setSexo('F');
        selected.setEstado("1");
        selected.setZonaResidencia('U');
        this.tipoIdentificacion = new TipoIdentificacion();
        this.raza = new Raza();
        facultad = new Facultad();
        programa = new Programas();
        estadoCivil = new EstadoCivil();
        escolaridad = new Escolaridad();
        estrato = new Estrato();
        categoriaAfiliado = new CategoriaAfiliado();
        tipoAfiliado = new TipoAfiliado();
        tipoRegimen = new TipoRegimen();
        eps = new Eps();
        municipioResidencia = new Municipio();
        deptoResidencia = new Depto();
        municipioNacimiento = new Municipio();
        deptoNacimiento = new Depto();
        parentesco = new Parentesco();

    }

    public void seleccionar_CargarPaciente(Paciente selected) {
        this.selected = selected;
        this.raza = selected.getRaza();
        this.tipoIdentificacion = selected.getTipoIdentificacion();
        this.facultad = selected.getFacultad();
        this.programa = selected.getPrograma();
        this.estadoCivil = selected.getEstadoCivil();
        this.escolaridad = selected.getEscolaridad();
        this.estrato = selected.getEstrato();
        this.categoriaAfiliado = selected.getCategoriaAfiliado();
        this.tipoAfiliado = selected.getTipoAfiliado();
        this.tipoRegimen = selected.getTipoRegimen();
        this.eps = selected.getEps();
        this.municipioResidencia = selected.getMunicipioResidencia();
        this.deptoResidencia = selected.getDeptoResidencia();
        this.municipioNacimiento = selected.getMunicipioNacimiento();
        this.deptoNacimiento = selected.getDeptoNacimiento();
        this.parentesco = selected.getParentesco();

    }

    public void limpiarcamposformulario() {
        this.limpiarCampos();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("PacienteEditForm");
        requestContext.update("PacienteCreateForm:panel");
        requestContext.update("PF('RegistroExitoso').hide()");
        requestContext.execute("PF('ActualizacionExitosa').hide()");
    }

    public void limpiarcamposformularioRegistro() {
        this.limpiarCampos();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("PacienteCreateForm:panel");
        requestContext.update("PF('RegistroExitoso').hide()");
    }

    private void asignarFechaApertura() {
        GregorianCalendar c = new GregorianCalendar();
        selected.setFechaApertura(c.getTime());
    }

    public String tipoListarPacientes(int a) {
        this.busquedaPaciente = "";
        tipoListar = a;
        switch (a) {
            case 1:
                /*Modelo utilizado por la tabla para cargar los registros por bloques.*/
                this.dataModel = new LazyDataModel<Paciente>() {
                    @Override
                    public List<Paciente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                        setRowCount(ejbFacade.count());

                        int[] range = {first, first + pageSize};
                        return ejbFacade.findRange(range);
                    }
                };
                break;

            case 2:
                this.dataModel = new LazyDataModel<Paciente>() {
                    @Override
                    public List<Paciente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                        setRowCount(ejbFacade.numeroPacientesConHistoria());

                        int[] range = {first, first + pageSize};
                        return ejbFacade.findByPacientesConHistoria(range);
                    }
                };
                break;
            case 3:
                this.dataModel = new LazyDataModel<Paciente>() {
                    @Override
                    public List<Paciente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                        setRowCount(ejbFacade.numeroPacientesSinHistoria());

                        int[] range = {first, first + pageSize};
                        return ejbFacade.findByPacientesSinHistoria(range);
                    }
                };
                break;
        }

        return "/usuariodelsistema/sexualidad/paciente/list.xhtml?faces-redirect=true";
    }

    public String limpiarBusquedaAndRedirect() {
        this.busquedaPaciente = "";
        tipoListarPacientes(this.tipoListar);
        return "/usuariodelsistema/sexualidad/paciente/list.xhtml?faces-redirect=true";
    }

    public void resetBusqueda() {
        this.busquedaPaciente = "";
        tipoListarPacientes(this.tipoListar);
    }

    public String tipoBuscarPacientes() {

        switch (tipoListar) {
            case 1:
                /*Modelo utilizado por la tabla para cargar los registros por bloques.*/
                this.dataModel = new LazyDataModel<Paciente>() {
                    @Override
                    public List<Paciente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                        setRowCount(ejbFacade.numeroPacientesBusqueda(busquedaPaciente));

                        int[] range = {first, first + pageSize};
                        return ejbFacade.buscarPacienteWithRange(busquedaPaciente, range);
                    }
                };
                break;

            case 2:
                this.dataModel = new LazyDataModel<Paciente>() {
                    @Override
                    public List<Paciente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                        setRowCount(ejbFacade.numeroPacientesConHistoriaBusqueda(busquedaPaciente));

                        int[] range = {first, first + pageSize};
                        return ejbFacade.findByPacientesConHistoriaBusqueda(busquedaPaciente, range);
                    }
                };
                break;
            case 3:
                this.dataModel = new LazyDataModel<Paciente>() {
                    @Override
                    public List<Paciente> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                        setRowCount(ejbFacade.numeroPacientesSinHistoriaBusqueda(busquedaPaciente));

                        int[] range = {first, first + pageSize};
                        return ejbFacade.findByPacientesSinHistoriaBusqueda(busquedaPaciente, range);
                    }
                };
                break;
        }

        return "/usuariodelsistema/sexualidad/paciente/list.xhtml?faces-redirect=true";

    }

    public void terminarRegistro(CargarVistaController cargarVistaController) {
        limpiarCampos();
        items = ejbFacade.findAll();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("form_registrar");
        requestContext.execute("PF('RegistroExitoso').hide()");
        cargarVistaController.cargarListaPaciente();
    }

    public void editar() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        try {

            selected.setTipoIdentificacion(tipoIdentificacion);
            selected.setFacultad(facultad);
            selected.setPrograma(programa);
            selected.setEstadoCivil(estadoCivil);
            selected.setEscolaridad(escolaridad);
            selected.setEstrato(estrato);
            selected.setCategoriaAfiliado(categoriaAfiliado);
            selected.setTipoAfiliado(tipoAfiliado);
            selected.setTipoRegimen(tipoRegimen);
            selected.setEps(eps);
            selected.setMunicipioResidencia(municipioResidencia);
            selected.setDeptoResidencia(deptoResidencia);
            selected.setMunicipioNacimiento(municipioNacimiento);
            selected.setDeptoNacimiento(deptoNacimiento);
            selected.setParentesco(parentesco);
            selected.setRaza(raza);
            ejbFacade.edit(selected);
            /*FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Edición de paciente", "Los cambios en la información del paciente fueron guardados con éxito.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);*/
            limpiarCampos();
            requestContext.execute("PF('ActualizacionExitosa').show()");
            //return "list.xhtml";
        } catch (Exception e) {
            /*FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Edición de paciente", "Los cambios en la información del paciente NO fueron guardados con éxito.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);*/
            requestContext.execute("PF('ActualizacionNoExitosa').show()");
            //return "./";
        }
    }

    public int getTipoListar() {
        return tipoListar;
    }

    public void onChangeTipoIdentificacion() {
        if (tipoIdentificacion != null) {
            String nombre = tipoIdentificacion.getNombre();
            if (nombre != null) {
                selected.setIdentificacion(null);
                if (nombre.equals(PASAPORTE)) {
                    tipo_identificacion = "./entrada_identificacion/alfanumerica.xhtml";
                } else if (nombre.equals(adulto_sin_identificacion) || nombre.equals(menor_sin_identificacion)) {
                    tipo_identificacion = "./entrada_identificacion/no_identificacion.xhtml";
                } else {
                    tipo_identificacion = "./entrada_identificacion/numerica.xhtml";
                }
            }

        } else {
            tipo_identificacion = "./entrada_identificacion/numerica.xhtml";
        }

    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Paciente getPaciente(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Paciente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Paciente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Paciente.class)
    public static class PacienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PacienteController controller = (PacienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pacienteController");
            return controller.getPaciente(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Paciente) {
                Paciente o = (Paciente) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Paciente.class.getName()});
                return null;
            }
        }

    }

}
