using REQCYCLER.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace REQCYCLER.Controllers
{
    public class RequisitoController : Controller
    {
        public ActionResult Index(int ProjetoId)
        {
            Session["ProjetoID"] = ProjetoId;
            return View();
        }

        public PartialViewResult GerarTabelaPacoteRequisito()
        {
            int ProjetoId = (int)Session["ProjetoID"];
            RequisitoViewModel requisitoVM = CarregarRequisitos(ProjetoId);

            return PartialView("TabelaPacoteRequisito", CarregarRequisitos((int)Session["ProjetoID"]));
        }

        public PartialViewResult GerarModalCadastroRequisito()
        {

            return PartialView("FormCadastroRequisito", new Requisito());
        }

        public RequisitoViewModel CarregarRequisitos(Int32 ProjetoId)
        {
            RequisitoViewModel requisitoModel = new RequisitoViewModel();

            //Inicializar listas Requisito Model
            requisitoModel.DicionarioPacoteRequisito = new Dictionary<Pacote, List<RequisitoTelaViewModel>>();
            requisitoModel.ListaPacote = new List<Pacote>();
            requisitoModel.ListaRequisito = new List<RequisitoTelaViewModel>();

            using (ReqcyclerEntities db = new ReqcyclerEntities())
            {
                //Carregar Pacotes
                var listaPacotes = (from pct in db.Pacote
                                    where pct.projetoId == ProjetoId
                                    select pct).ToList();

                Session["ListaPacotes"] = listaPacotes;

                requisitoModel.ListaPacote = listaPacotes;

                //Carregar Requisitos Pacote
                foreach (Pacote tmpPacote in listaPacotes)
                {

                    var teste = (from pr in db.PacoteRequisito
                                where pr.pacoteId == tmpPacote.id
                                select pr.requisitoId).ToList();

                    List<RequisitoTelaViewModel> RequisitoTelaViewModel = new List<RequisitoTelaViewModel>();

                    foreach (Int32 index in teste)
                    {
                        var single = (from r in db.Requisito
                                      where r.id == index
                                      select new RequisitoTelaViewModel
                                      {
                                          id = r.id,
                                          Nome = r.nome,
                                          TipoRequisito = r.tipoRequisito,
                                          Complexidade = r.complexidade
                                      }).FirstOrDefault();

                        RequisitoTelaViewModel.Add(single);
                    }



                    requisitoModel.DicionarioPacoteRequisito.Add(tmpPacote, RequisitoTelaViewModel);
                }
                

                return requisitoModel;
            }
        }

        [HttpPost]
        public ActionResult CriarRequisito(Requisito requisitoModelo)
        {
            //Salvar Requisito

            requisitoModelo.projetoId = (int)Session["ProjetoID"];

            using (ReqcyclerEntities db = new ReqcyclerEntities())
            {
                db.Requisito.Add(requisitoModelo);
                db.SaveChanges();

                //Associar Pacote e Requisito
                db.PacoteRequisito.Add(new PacoteRequisito { pacoteId = requisitoModelo.pacoteId, requisitoId = requisitoModelo.id });
                db.SaveChanges();
            }

            int ProjetoId = (int)Session["ProjetoID"];

            return Redirect("~/Requisito/Index?ProjetoId=" + ProjetoId);
        }
    }
}
