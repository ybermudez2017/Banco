package co.edu.usbcali.demo.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.demo.logica.ITiposUsuariosLogica;
import co.edu.usbcali.demo.logica.IUsuariosLogica;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

@ManagedBean
@ViewScoped
public class UsuariosVista {
	@ManagedProperty("#{usuariosLogica}")
	private IUsuariosLogica usuariosLogica;

	@ManagedProperty("#{tiposUsuariosLogica}")
	private ITiposUsuariosLogica tiposUsuariosLogica;

	private List<Usuarios> losUsuarios;

	private InputText txtCedula;
	private SelectOneMenu somTiposUsuarios;
	private InputText txtNombre;
	private InputText txtLogin;
	private Password txtClave;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;

	private List<SelectItem> losTiposUsuariosSelectItem;

	public List<SelectItem> getLosTiposUsuariosSelectItem() {
		if (losTiposUsuariosSelectItem == null) {
			losTiposUsuariosSelectItem = new ArrayList<SelectItem>();
			List<TiposUsuarios> losTiposUsuarios = tiposUsuariosLogica.consultar();
			for (TiposUsuarios tiposUsuarios : losTiposUsuarios) {
				losTiposUsuariosSelectItem
						.add(new SelectItem(tiposUsuarios.getTusuCodigo(), tiposUsuarios.getTusuNombre()));
			}
		}
		return losTiposUsuariosSelectItem;
	}

	public void setLosTiposUsuariosSelectItem(List<SelectItem> losTiposUsuariosSelectItem) {
		this.losTiposUsuariosSelectItem = losTiposUsuariosSelectItem;
	}

	public String crearAction() {
		try {
			Long usuCedula = Long.parseLong(txtCedula.getValue().toString());
			Usuarios usuarios = new Usuarios();
			usuarios.setUsuCedula(usuCedula);
			usuarios.setUsuNombre(txtNombre.getValue().toString());
			usuarios.setUsuLogin(txtLogin.getValue().toString());
			usuarios.setUsuClave(txtClave.getValue().toString());

			Long tusuCodigo = Long.parseLong(somTiposUsuarios.getValue().toString());
			TiposUsuarios tiposUsuarios = tiposUsuariosLogica.consultarPorId(tusuCodigo);

			usuarios.setTiposUsuarios(tiposUsuarios);

			usuariosLogica.crear(usuarios);
			losUsuarios = null;

			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se creo con exito", ""));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}

	public String modificarAction() {
		try {
			Long usuCedula = Long.parseLong(txtCedula.getValue().toString());
			Usuarios usuarios = new Usuarios();
			usuarios.setUsuCedula(usuCedula);
			usuarios.setUsuNombre(txtNombre.getValue().toString());
			usuarios.setUsuLogin(txtLogin.getValue().toString());
			usuarios.setUsuClave(txtClave.getValue().toString());

			Long tusuCodigo = Long.parseLong(somTiposUsuarios.getValue().toString());
			TiposUsuarios tiposUsuarios = tiposUsuariosLogica.consultarPorId(tusuCodigo);

			usuarios.setTiposUsuarios(tiposUsuarios);

			usuariosLogica.modificar(usuarios);
			losUsuarios = null;

			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario modificado exitosamente", ""));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";

	}

	public String borrarAction() {
		try {
			Long usuCedula = Long.parseLong(txtCedula.getValue().toString());
			Usuarios usuarios = new Usuarios();
			usuarios.setUsuCedula(usuCedula);
			usuarios.setUsuNombre(txtNombre.getValue().toString());
			usuarios.setUsuLogin(txtLogin.getValue().toString());
			usuarios.setUsuClave(txtClave.getValue().toString());

			Long tusuCodigo = Long.parseLong(somTiposUsuarios.getValue().toString());
			TiposUsuarios tiposUsuarios = tiposUsuariosLogica.consultarPorId(tusuCodigo);

			usuarios.setTiposUsuarios(tiposUsuarios);

			usuariosLogica.borrar(usuarios);
			losUsuarios = null;

			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Ususario borrado Exitosamente", ""));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		return "";
	}

	public String limpiarAction() {
		txtCedula.resetValue();
		losUsuarios = null;
		limpiar();
		return "";
	}

	public void txtCedulaListener() {
		Long usuCedula = 0L;
		Usuarios entity = null;
		try {
			usuCedula = Long.parseLong(txtCedula.getValue().toString());
			entity = usuariosLogica.consultarPorId(usuCedula);
		} catch (Exception e) {
		}

		if (entity == null) {
			limpiar();
		} else {
			btnCrear.setDisabled(true);
			btnModificar.setDisabled(false);
			btnBorrar.setDisabled(false);

			txtNombre.setValue(entity.getUsuNombre());
			txtLogin.setValue(entity.getUsuLogin());

			somTiposUsuarios.setValue(entity.getTiposUsuarios().getTusuCodigo());
		}
	}

	private void limpiar() {
		btnCrear.setDisabled(false);
		btnModificar.setDisabled(true);
		btnBorrar.setDisabled(true);

		txtNombre.resetValue();
		txtLogin.resetValue();
		txtClave.resetValue();
		somTiposUsuarios.resetValue();
	}

	public IUsuariosLogica getUsuariosLogica() {
		return usuariosLogica;
	}

	public void setUsuariosLogica(IUsuariosLogica usuariosLogica) {
		this.usuariosLogica = usuariosLogica;
	}

	public ITiposUsuariosLogica getTiposUsuariosLogica() {
		return tiposUsuariosLogica;
	}

	public void setTiposUsuariosLogica(ITiposUsuariosLogica tiposUsuariosLogica) {
		this.tiposUsuariosLogica = tiposUsuariosLogica;
	}

	public List<Usuarios> getLosUsuarios() {
		if (losUsuarios == null) {
			losUsuarios = usuariosLogica.consultar();
		}
		return losUsuarios;
	}

	public void setLosUsuarios(List<Usuarios> losUsuarios) {
		this.losUsuarios = losUsuarios;
	}

	public InputText getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(InputText txtCedula) {
		this.txtCedula = txtCedula;
	}

	public SelectOneMenu getSomTiposUsuarios() {
		return somTiposUsuarios;
	}

	public void setSomTiposUsuarios(SelectOneMenu somTiposUsuarios) {
		this.somTiposUsuarios = somTiposUsuarios;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(InputText txtLogin) {
		this.txtLogin = txtLogin;
	}

	public Password getTxtClave() {
		return txtClave;
	}

	public void setTxtClave(Password txtClave) {
		this.txtClave = txtClave;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public CommandButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

}
