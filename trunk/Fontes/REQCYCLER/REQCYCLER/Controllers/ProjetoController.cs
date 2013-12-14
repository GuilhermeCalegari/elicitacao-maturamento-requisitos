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

        public ActionResult Index()
        {
            ViewBag.PaginaAtual = "HOME";
            var projeto = db.Projeto.Include(p => p.Area);

            Int32 usuarioID = (Int32)Session["UsuarioLogadoID"];

            var listaProjetos = (from pu in db.ProjetoUsuario
                                 join p in db.Projeto on pu.projetoId equals p.id
                                 where pu.usuarioId == usuarioID
                                 select new ProjetoViewModel
                                 {
                                     ProjetoId = p.id,
                                     NomeProjeto = p.nome,
                                     Descricao = p.descricao,
                                     Objetivo = p.objetivo
                                 }).ToList();

            Session["NumProjetos"] = listaProjetos.Count;
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

            //Criar pacotes para o projeto recém criado automaticamente

            for (int i = 0; i < 3; i++)
            {
                Pacote pacote = new Pacote();

                pacote.projetoId = tmpIdProjeto;
                pacote.NOME = "Pacote - 0" + i;
                pacote.status = "Aberto";

                db.Pacote.Add(pacote);
                db.SaveChanges();
            }

            return RedirectToAction("Index");
        }

        public ActionResult AcessarProjeto(Int32 ProjetoId)
        {
            var projetoSelecionado = (from p in db.Projeto
                                      where p.id == ProjetoId
                                      select new ProjetoViewModel
                                      {
                                          ProjetoId = p.id,
                                          NomeProjeto = p.nome,
                                          Objetivo = p.objetivo,
                                          Descricao = p.descricao
                                      }).FirstOrDefault();

            Session["DadosProjeto"] = projetoSelecionado; 
            return View(projetoSelecionado);
        }

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

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }
    }
}