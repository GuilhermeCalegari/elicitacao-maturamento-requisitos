using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace REQCYCLER.Models
{
    public class FluxoViewModel
    {
        public List<UsuarioFluxo> ListaUsuarios { get; set; }
        public List<PapelFluxo> ListaPapeis { get; set; }

         public FluxoViewModel()
        {
            using (ReqcyclerEntities dbLocal = new ReqcyclerEntities())
            {
                var tmpUsuarios = (from u in dbLocal.Usuario.ToList()
                                   select new UsuarioFluxo
                                   {
                                       Id = u.id,
                                       Nome = u.nome
                                   }).ToList();

                var tmpPapeis = (from p in dbLocal.PapelUsuario.ToList()
                                 select new PapelFluxo
                                  {
                                      Id = p.id,
                                      Valor = p.valor
                                  }).ToList();

                this.ListaUsuarios = tmpUsuarios;
                this.ListaPapeis = tmpPapeis;
             }
        }
    }

    public class UsuarioFluxo
    {
        public int Id { get; set; }
        public String Nome { get; set; }
    }

    public class PapelFluxo
    {
        public int Id { get; set; }
        public String Valor { get; set; }
    }
}