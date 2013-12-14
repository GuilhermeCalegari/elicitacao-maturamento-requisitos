using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace REQCYCLER.Models
{
    public class RequisitoViewModel
    {
        public List<Pacote> ListaPacote { get; set; }
        public List<RequisitoTelaViewModel> ListaRequisito { get; set; }
        public Dictionary<Pacote, List<RequisitoTelaViewModel>> DicionarioPacoteRequisito { get; set; }
    }

    public class RequisitoTelaViewModel
    {
        public String Nome { get; set; }
        public String TipoRequisito { get; set; }
        public String Complexidade { get; set; }
        public int id { get; set; }
    }
}