using REQCYCLER.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace REQCYCLER.Controllers
{
    public class ProjetoController : Controller
    {
        private ReqcyclerEntities db = new ReqcyclerEntities();

        //
        // GET: /Projeto/

        public ActionResult Index()
        {
            ViewBag.PaginaAtual = "HOME";
            var projeto = db.Projeto.Include(p => p.Area);

            var listaProjetos = (from p in db.Projeto.ToList()
                                 select new ProjetoViewModel
                                 {
                                     ProjetoId = p.id,
                                     NomeProjeto = p.nome,
                                 }).ToList();




            ViewBag.ListaProjeto = listaProjetos;

            return View();
        }

        [HttpPost]
        public Boolean CriarProjeto(Projeto projeto)
        {
            byte[] data;
            using (Stream inputStream = Request.InputStream)
            {
                MemoryStream memoryStream = inputStream as MemoryStream;
                if (memoryStream == null)
                {
                    memoryStream = new MemoryStream();
                    inputStream.CopyTo(memoryStream);
                }

                data = memoryStream.ToArray();
            }

            projeto.logotipo = data;


            Session["TempProjeto"] = projeto;

            return true;
        }

        public FileContentResult CarregarLogotipo(int projetoID)
        {
            Byte[] image = db.Projeto.Find(projetoID).logotipo;

            return new FileContentResult(image, "image/jpeg");
        }

        [HttpPost]
        public PartialViewResult DefinirNiveis(Projeto projeto)
        {
            ViewBag.NumeroNiveis = projeto.NumeroNiveis;
            List<FluxoViewModel> ListaFluxo = new List<FluxoViewModel>();

            for (int i = 0; i < projeto.NumeroNiveis; i++)
            {
                ListaFluxo.Add(new FluxoViewModel());
            }

            return PartialView("GeraNiveisAprovacao", ListaFluxo);
        }

        [HttpPost]
        public ActionResult SalvarProjetoFinal(List<Int32> ListaUsuarios, List<Int32> ListaPapeis)
        {
            Int32 tmpIdProjeto;
            Projeto referenciaProjeto;
            ProjetoUsuario projetoUsuario;
            Int32 tmpIdUsuarioProjeto;
            FluxoAprovacaoProjeto fluxoAprovacaoProjeto;

            //Salvar Projeto Base
            referenciaProjeto = (Projeto)Session["TempProjeto"];
            Session.Remove("TempProjeto");

            db.Projeto.Add(referenciaProjeto);
            db.SaveChanges();

            tmpIdProjeto = referenciaProjeto.id;

            //Associar Projeto e Usuários
            for (int i = 0; i < ListaUsuarios.Count; i++)
			{
			     projetoUsuario = new ProjetoUsuario();

                 projetoUsuario.usuarioId = ListaUsuarios[i];
                 projetoUsuario.papelUsuarioId = ListaPapeis[i];
                 projetoUsuario.projetoId = tmpIdProjeto;

                 db.ProjetoUsuario.Add(projetoUsuario);
                 db.SaveChanges();

                 tmpIdUsuarioProjeto = projetoUsuario.id;

                //Associar ProjetoUsuario ao Fluxo Projeto

                 fluxoAprovacaoProjeto = new FluxoAprovacaoProjeto();

                 fluxoAprovacaoProjeto.projetoUsuarioId = tmpIdUsuarioProjeto;
                 fluxoAprovacaoProjeto.ordem = i + 1;

                 db.FluxoAprovacaoProjeto.Add(fluxoAprovacaoProjeto);
                 db.SaveChanges();
			}
            return RedirectToAction("Index");
        }
        


        //
        // GET: /Projeto/Details/5

        public ActionResult Details(int id = 0)
        {
            Projeto projeto = db.Projeto.Find(id);
            if (projeto == null)
            {
                return HttpNotFound();
            }
            return View(projeto);
        }

        //
        // GET: /Projeto/Create

        public ActionResult Create()
        {
            ViewBag.areaResponsavelId = new SelectList(db.Area, "id", "valor");
            return View();
        }

        //
        // POST: /Projeto/Create

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Projeto projeto)
        {
            if (ModelState.IsValid)
            {
                db.Projeto.Add(projeto);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.areaResponsavelId = new SelectList(db.Area, "id", "valor", projeto.areaResponsavelId);
            return View(projeto);
        }

        //
        // GET: /Projeto/Edit/5

        public ActionResult Edit(int id = 0)
        {
            Projeto projeto = db.Projeto.Find(id);
            if (projeto == null)
            {
                return HttpNotFound();
            }
            ViewBag.areaResponsavelId = new SelectList(db.Area, "id", "valor", projeto.areaResponsavelId);
            return View(projeto);
        }

        //
        // POST: /Projeto/Edit/5

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(Projeto projeto)
        {
            if (ModelState.IsValid)
            {
                db.Entry(projeto).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.areaResponsavelId = new SelectList(db.Area, "id", "valor", projeto.areaResponsavelId);
            return View(projeto);
        }

        //
        // GET: /Projeto/Delete/5

        public ActionResult Delete(int id = 0)
        {
            Projeto projeto = db.Projeto.Find(id);
            if (projeto == null)
            {
                return HttpNotFound();
            }
            return View(projeto);
        }

        //
        // POST: /Projeto/Delete/5

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Projeto projeto = db.Projeto.Find(id);
            db.Projeto.Remove(projeto);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }
    }
}