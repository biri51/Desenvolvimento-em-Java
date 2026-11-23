package cadastroee.model;
import java.io.Serializable;
import jakarta.persistence.*;
@Entity
@Table(name="Produto", schema="dbo")
public class Produto implements Serializable {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="idProduto") private Integer idProduto;
 @Column(name="nome",nullable=false,length=100) private String nome;
 @Column(name="quantidade",nullable=false) private Integer quantidade;
 @Column(name="precoVenda",nullable=false) private Float precoVenda;
 public Produto() {}
 public Integer getIdProduto(){return idProduto;}
 public void setIdProduto(Integer id){this.idProduto=id;}
 public String getNome(){return nome;}
 public void setNome(String nome){this.nome=nome;}
 public Integer getQuantidade(){return quantidade;}
 public void setQuantidade(Integer quantidade){this.quantidade=quantidade;}
 public Float getPrecoVenda(){return precoVenda;}
 public void setPrecoVenda(Float precoVenda){this.precoVenda=precoVenda;}
}
