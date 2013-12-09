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
            ViewData["ListaProjeto"] = projeto.ToList();

            return View();
        }

        [HttpPost]
        public Boolean CriarProjeto(Projeto projeto, HttpPostedFileBase imageData)
        {
            if (imageData != null)
            {
                byte[] data;
                using (Stream inputStream = imageData.InputStream)
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
            }

            Session["TempProjeto"] = projeto;
            Session["TempFluxoAprovacao"] = new FluxoAprovacaoProjeto();

            return true;
        }

        public FileContentResult CarregarLogotipo(int projetoID)
        {

            var stream = (from proj in db.Projeto
                          where proj.id == projetoID
                          select proj.logotipo)
                          .FirstOrDefault();

            return File(stream, "image/jpeg");
        }

        [HttpPost]
        public PartialViewResult DefinirNiveis(Projeto projeto)
        {
            var tmpPapeis = from p in db.PapelUsuario
                            select p;

            var tmpUsuarios = from u in db.Usuario
                              select u;

            ViewBag.ListaUsuarios = new SelectList(tmpUsuarios, "id", "nome");
            ViewBag.ListaPapeis = new SelectList(tmpPapeis, "id", "valor");
            ViewBag.NumeroNiveis = projeto.NumeroNiveis;

            return PartialView("GeraNiveisAprovacao");
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