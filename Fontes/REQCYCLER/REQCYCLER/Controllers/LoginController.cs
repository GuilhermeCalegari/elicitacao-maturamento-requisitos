using REQCYCLER.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace REQCYCLER.Controllers
{
    public class LoginController : Controller
    {
        [AllowAnonymous]
        public ActionResult Index()
        {
            return View();
        }

        [HttpPost]
        [AllowAnonymous]
        public ActionResult Login(LoginModel model)
        {
            using (var db = new ReqcyclerEntities())
            {
                var tmpUser = (db.Usuario
                    .Where(u => u.email == model.LoginMail)
                    .FirstOrDefault()) as Usuario;


                if (tmpUser != null)
                {
                    var numProjetos = (from projetoUsuario in db.ProjetoUsuario
                                       where projetoUsuario.id.Equals(tmpUser.id)
                                       select projetoUsuario).Count();

                    Session["UsuarioLogado"] = ((Usuario)tmpUser).nome;
                    Session["NumProjetos"] = (Int32)numProjetos;
                    return RedirectToAction("Index", "Home");
                }
                else
                {
                    return RedirectToAction("Index", "Login");
                }
            }
        }
    }
}
