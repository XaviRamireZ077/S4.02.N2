package cat.itacademy.barcelonactiva.ramirez.xavi.s04.t02.n02.Model.repository;

import cat.itacademy.barcelonactiva.ramirez.xavi.s04.t02.n02.Model.Fruita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitaRepository extends JpaRepository<Fruita, Integer> {
}
