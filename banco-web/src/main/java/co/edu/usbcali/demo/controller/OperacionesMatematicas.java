package co.edu.usbcali.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.dto.Resultado;

@RestController
@RequestMapping("/Calculadora")
public class OperacionesMatematicas {
	
	@GetMapping("/sumar/{n1}/{n2}")
	public Resultado sumar(@PathVariable("n1") Integer n1, @PathVariable("n2") Integer n2) {
		Resultado resultado=new Resultado();
		resultado.setValor(n1+n2);
		return resultado;
	}
	@GetMapping("/restar/{n1}/{n2}")
	public Resultado restar(@PathVariable("n1") Integer n1, @PathVariable("n2") Integer n2) {
		Resultado resultado=new Resultado();
		resultado.setValor(n1-n2);
		return resultado;
	}
	@GetMapping("/multiplicar/{n1}/{n2}")
	public Resultado multiplicar(@PathVariable("n1") Integer n1, @PathVariable("n2") Integer n2) {
		Resultado resultado=new Resultado();
		resultado.setValor(n1*n2);
		return resultado;
	}
	@GetMapping("/dividir/{n1}/{n2}")
	public Resultado dividir(@PathVariable("n1") Integer n1, @PathVariable("n2") Integer n2) {
		Resultado resultado=new Resultado();
		if (n2==0) {
			resultado.setValor(null);
		}
		else {
			resultado.setValor(n1/n2);
		}
		return resultado;
	}
}