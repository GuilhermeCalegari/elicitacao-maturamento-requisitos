//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace REQCYCLER
{
    using System;
    using System.Collections.Generic;
    
    public partial class ProjetoRequisito
    {
        public int id { get; set; }
        public Nullable<int> projetoId { get; set; }
        public Nullable<int> requisitoId { get; set; }
        public Nullable<int> pacoteId { get; set; }
    
        public virtual Requisito Requisito { get; set; }
    }
}