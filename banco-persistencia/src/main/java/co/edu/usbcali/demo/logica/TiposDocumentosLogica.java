package co.edu.usbcali.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.usbcali.demo.dao.ITiposDocumentosDAO;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@Service
@Scope("singleton")
public class TiposDocumentosLogica implements ITiposDocumentosLogica {

	@Autowired
	private ITiposDocumentosDAO tiposDocumentosDAO;
	
	@Override
	public TiposDocumentos consultarPorId(long tdocCodigo) {
		return tiposDocumentosDAO.consultarPorId(tdocCodigo);
	}

	@Override
	public List<TiposDocumentos> consultar() {
		return tiposDocumentosDAO.consultar();
	}

}
