package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;


public class ClientesDTO {
	
	@NotNull
	private long cliId;
	@NotNull
	private String cliNombre;
	@NotNull
	private String cliDireccion;
	@NotNull
	private String cliTelefono;
	@Email
	@NotNull
	private String cliMail;
	
	private Long codigoTipoDocumento;
	
	private Integer codigoError;
	private String mensajeError;
	
	
	
	public ClientesDTO() {
	
	}
	
	
	
	



	public ClientesDTO(long cliId, String cliNombre, String cliDireccion, String cliTelefono, String cliMail,
			Long codigoTipoDocumento, Integer codigoError, String mensajeError) {
		super();
		this.cliId = cliId;
		this.cliNombre = cliNombre;
		this.cliDireccion = cliDireccion;
		this.cliTelefono = cliTelefono;
		this.cliMail = cliMail;
		this.codigoTipoDocumento = codigoTipoDocumento;
		this.codigoError = codigoError;
		this.mensajeError = mensajeError;
	}







	public Long getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}







	public void setCodigoTipoDocumento(Long codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}







	public Integer getCodigoError() {
		return codigoError;
	}



	public void setCodigoError(Integer codigoError) {
		this.codigoError = codigoError;
	}



	public String getMensajeError() {
		return mensajeError;
	}



	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}



	public long getCliId() {
		return cliId;
	}
	public void setCliId(long cliId) {
		this.cliId = cliId;
	}
	public String getCliNombre() {
		return cliNombre;
	}
	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre;
	}
	public String getCliDireccion() {
		return cliDireccion;
	}
	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}
	public String getCliTelefono() {
		return cliTelefono;
	}
	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}
	public String getCliMail() {
		return cliMail;
	}
	public void setCliMail(String cliMail) {
		this.cliMail = cliMail;
	}
	
	

}
