package cat.itacademy.barcelonactiva.ramirez.xavi.s04.t02.n02.Controller;

import cat.itacademy.barcelonactiva.ramirez.xavi.s04.t02.n02.Model.Fruita;
import cat.itacademy.barcelonactiva.ramirez.xavi.s04.t02.n02.Model.repository.FruitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fruita")
public class FruitaController {

    @Autowired
    FruitaRepository fruitaRepository;

    @PostMapping("/add")
    public ResponseEntity<Fruita> addFruit(@RequestBody Fruita fruita) {
        try {
            Fruita _fruita = fruitaRepository
                    .save(new Fruita(fruita.getNom(), fruita.getQuantitatQuilos()));
            return new ResponseEntity<>(_fruita, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fruita> updateFruit(@PathVariable("id") int id, @RequestBody Fruita fruita) {
        Optional<Fruita> fruitData = fruitaRepository.findById(id);

        if (fruitData.isPresent()) {
            Fruita _fruita = fruitData.get();
            _fruita.setNom(fruita.getNom());
            _fruita.setQuantitatQuilos(fruita.getQuantitatQuilos());
            //_fruita.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(fruitaRepository.save(_fruita), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteFruitbyId(@PathVariable("id") int id) {
        try {
            fruitaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruita> getFruitById(@PathVariable("id") int id) {
        Optional<Fruita> fruitData = fruitaRepository.findById(id);

        if (fruitData.isPresent()) {
            return new ResponseEntity<>(fruitData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Fruita>> getAllFruits(@RequestParam(required = false) String nom) {
        try {
            List<Fruita> fruites = fruitaRepository.findAll();

            if (fruites.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(fruites, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
