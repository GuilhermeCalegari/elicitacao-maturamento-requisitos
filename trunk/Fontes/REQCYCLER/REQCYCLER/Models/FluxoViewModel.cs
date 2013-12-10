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
        public int[] UsuariosSelecionados { get; set; }


        public FluxoViewModel()
        {
            using (ReqcyclerEntities dbLocal = new ReqcyclerEntities())
            {
                  var tmpUsuarios = (from u in dbLocal.Usuario
                                    select u).ToList();

                  //var tmpPapeis = (from p in dbLocal.PapelUsuario
                  //                   select p).ToList();

                  this.ListaUsuarios = new List<UsuarioFluxo>();
                  //this.ListaPapeis = new List<SelectListItem>();

                  foreach (var item in tmpUsuarios)
                  {
                      this.ListaUsuarios.Add(new UsuarioFluxo {  Id = item.id, Nome = item.nome});
                  }

                  //foreach (var item in tmpPapeis)
                  //{
                  //    this.ListaPapeis.Add(new SelectListItem { Value = item.id.ToString(), Text = item.valor });
                  //}
            }
        }
    }

    public class UsuarioFluxo
    {
        public int Id { get; set; }
        public String Nome { get; set; }
    }
}