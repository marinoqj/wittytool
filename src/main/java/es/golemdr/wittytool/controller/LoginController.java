package es.golemdr.wittytool.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.golemdr.wittytool.controller.constantes.ForwardConstants;
import es.golemdr.wittytool.controller.constantes.UrlConstants;
import es.golemdr.wittytool.domain.Usuario;
import es.golemdr.wittytool.ext.Constantes;
import es.golemdr.wittytool.service.UsuariosService;

 
@Controller
public class LoginController {
	
	private static final Logger log = LogManager.getLogger(LoginController.class);

	@Autowired
	private UsuariosService usuariosService;
	
	@GetMapping(value=UrlConstants.URL_LOGIN)
	public String verlogin(Model model,HttpServletRequest request) {
		
		return ForwardConstants.FWD_LOGIN;
	}
	
	@PostMapping(value=UrlConstants.URL_LOGIN)
	public String login(Model model,HttpServletRequest request) {
		
		return ForwardConstants.FWD_HOME;
	}
	
	@GetMapping(value=UrlConstants.URL_LOGIN_FAILURE)
 	public String loginFailure(Model model) {
		
		String mensaje = "Usuario o password incorrectos";
		
		model.addAttribute("mensaje", mensaje);
		
		return ForwardConstants.FWD_LOGIN;
	}
	
    @GetMapping(value=UrlConstants.URL_ACCESO_OK)
    public String loginPage() {
    	
    	return ForwardConstants.FWD_HOME;
    }
    
	@GetMapping(value=UrlConstants.URL_ENTRADA_APLICACION)
	public String inicio(Model model,HttpServletRequest request) {
		
		String destino = null;
		
		String login = SecurityContextHolder.getContext().getAuthentication().getName(); // En este caso login y name son equivalentes
		Usuario usuarioLogado = new Usuario();
		usuarioLogado.setLogin(login);
		
		
		usuarioLogado = usuariosService.findUsuariosByExample(usuarioLogado, null).get(0);
		
		if(usuarioLogado.getCambiarPassword().equals(Constantes.SI)) {
				
			// TODO - Implementar cambio de password
			
		}else {

			HttpSession session = request.getSession(false);
			session.setAttribute(Constantes.ATRIBUTO_SESSION_USUARIO, usuarioLogado);
			
			log.info(Constantes.USUARIO_LOGADO_CORRECTAMENTE, usuarioLogado.getLogin());
			
			destino = ForwardConstants.FWD_HOME;

			
		}
		
		return destino;
	}
  

}
