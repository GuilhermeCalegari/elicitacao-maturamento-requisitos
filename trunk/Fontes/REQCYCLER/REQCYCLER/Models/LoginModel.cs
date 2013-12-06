using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;

namespace REQCYCLER.Models
{
    public class LoginModel
    {
        [EmailAddress]
        [DisplayName("E-mail")]
        [Required(ErrorMessage = "O campo 'E-mail' é de preenchimento obrigatório.")]
        public String LoginMail { get; set; }

        [PasswordPropertyText]
        [DisplayName("Senha")]
        [Required(ErrorMessage = "O campo 'Senha' é de preenchimento obrigatório.")]
        public String Senha { get; set; }
    }
}
