package es.golemdr.tragaldaba.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.golemdr.tragaldaba.domain.Pedido;

@Repository
public interface PedidosRepository extends JpaRepository<Pedido, Long>{

}
