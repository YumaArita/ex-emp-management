package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")

public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {

		return new InsertAdministratorForm();
	}

	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";

	}

	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form, @Validated BindingResult result ,
			  Model model)  {
		Administrator administrator = new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		administratorService.insert(administrator);
		 { if(result.hasErrors()){
				 return "toInsert()"; }

		return "redirect:/";}

	}

	// setUpLoginForm()を呼び出すことでログイン画面で受け取ったものを呼び出せる？
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}

	@Autowired
	private HttpSession session;

	@RequestMapping("/login")
	public String login(LoginForm form, Model model) {
		String mailAddres = form.getMailAddress();
		String password = form.getPassword();

		Administrator administrator = administratorService.login(mailAddres, password);
//		administrator ga null nara login sippai
		if (administrator == null) {
			model.addAttribute("message", "mailaddres or pass is invalid.");
			return "administrator/login";
//			  rikuesuto suko-pu ni
//			   era-messe-jiwo
//			   kakunousite roguigamenni modosu
		}
		session.setAttribute("administratorName", administrator.getName());
		return "dummy";
//		null janakereba loginseikou
//		   jyuugyouin itiran gamen ni
//		    senisaseru

	}

	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}

}
