package carros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carros.entity.Carro;
import carros.service.CarroService;

@RestController
@RequestMapping("/carros")
public class CarroController {

	@Autowired
	private CarroService carroService;
	
	@PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Carro carro) {
		
        try {
            String mensagem = this.carroService.save(carro);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deu Errado", HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Carro>> findAll(){
		
		try {
			List<Carro> carros = this.carroService.findAll();
			return new ResponseEntity<List<Carro>>(carros, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Carro> findById(@PathVariable int id){
		
		try {
			Carro carro = this.carroService.findById(id);
			return new ResponseEntity<Carro>(carro, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Carro carroAtualizado) {
        try {
            Carro carroExistente = carroService.findById(id);
            if (carroExistente != null) {
                carroService.update(id, carroAtualizado);
                return new ResponseEntity<>("Atualização bem-sucedida", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Carro não encontrado", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Deu Errado", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            Carro carroExistente = carroService.findById(id);
            if (carroExistente != null) {
                carroService.delete(id);
                return new ResponseEntity<>("Carro deletado com sucesso", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Carro não encontrado", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Deu Errado", HttpStatus.BAD_REQUEST);
        }
    }
	
}
