package co.edu.usbcali.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.dto.ClientesDTO;
import co.edu.usbcali.demo.logica.IClientesLogica;
import co.edu.usbcali.demo.logica.ITiposDocumentosLogica;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@RestController
@RequestMapping("/clientesRest")
public class ClientesRest {
	
	@Autowired
	private IClientesLogica clientesLogica;
	
	@Autowired
	private ITiposDocumentosLogica tiposDocumentosLogica;
	
	@PostMapping("/crear")
	public void crear(@RequestBody ClientesDTO clientesDTO)throws Exception{
		Clientes clientes=clientesDTOMapper(clientesDTO);
		
		clientesLogica.crear(clientes);
	}
	
	@PutMapping("/modificar")
	public void modificar(@RequestBody ClientesDTO clientesDTO)throws Exception{
		Clientes clientes=clientesDTOMapper(clientesDTO);
		
		clientesLogica.modificar(clientes);
	}
	
	@DeleteMapping("/borrar/{id}")
	public void borrar(@PathVariable("id") Long cliId)throws Exception{
		Clientes clientes=clientesLogica.consultarPorId(cliId);
		
		clientesLogica.borrar(clientes);
	}
	
	@GetMapping("/consultar/{id}")
	public ClientesDTO consultar(@PathVariable("id") Long cliId)throws Exception{
		ClientesDTO clientesDTO=null;
		Clientes clientes=clientesLogica.consultarPorId(cliId);
		if(clientes==null){
			clientesDTO=new ClientesDTO();
			clientesDTO.setCodigoError(10);
			clientesDTO.setMensajeError("El cliente con id:"+cliId+" no existe");
			return clientesDTO;
		}
		
		clientesDTO=clientesMapper(clientes);
		
		return clientesDTO;
	}
	
	@GetMapping("/consultarTodos/")
	public List<ClientesDTO> consultarTodos()throws Exception{
		List<ClientesDTO> losClientesDTO=new ArrayList<ClientesDTO>();
		List<Clientes> losClientes=clientesLogica.consultar();
		
		for (Clientes clientes : losClientes) {
			ClientesDTO clientesDTO=clientesMapper(clientes);
			losClientesDTO.add(clientesDTO);
		}
		
		
		return losClientesDTO;
	}
	
	private Clientes clientesDTOMapper(ClientesDTO clientesDTO){
		Clientes clientes=new Clientes();
		clientes.setCliDireccion(clientesDTO.getCliDireccion());
		clientes.setCliId(clientesDTO.getCliId());
		clientes.setCliMail(clientesDTO.getCliMail());
		clientes.setCliNombre(clientesDTO.getCliNombre());
		clientes.setCliTelefono(clientesDTO.getCliTelefono());
		TiposDocumentos tiposDocumentos=tiposDocumentosLogica.consultarPorId(clientesDTO.getCodigoTipoDocumento());
		clientes.setTiposDocumentos(tiposDocumentos);
		
		return clientes;
		
	}

	private ClientesDTO clientesMapper(Clientes clientes) {
		ClientesDTO clientesDTO=new ClientesDTO();
		clientesDTO.setCliDireccion(clientes.getCliDireccion());
		clientesDTO.setCliId(clientes.getCliId());
		clientesDTO.setCliMail(clientes.getCliMail());
		clientesDTO.setCliNombre(clientes.getCliNombre());
		clientesDTO.setCliTelefono(clientes.getCliTelefono());
		return clientesDTO;
	} 
	

}
