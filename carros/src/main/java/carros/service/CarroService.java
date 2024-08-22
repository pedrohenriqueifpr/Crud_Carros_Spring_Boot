package carros.service;

import org.springframework.stereotype.Service;
import carros.entity.Carro;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarroService {

    private List<Carro> carros = new ArrayList<>();

    public String save(Carro carro) {
        carros.add(carro);
        return "Salvo com Sucesso";
    }

    public List<Carro> findAll() {
        return new ArrayList<>(carros);
    }

    public Carro findById(int id) {
        for (Carro carro : carros) {
            if (carro.getId() == id) {
                return carro;
            }
        }
        return null;
    }


    public String update(int id, Carro carroAtualizado) {
        Carro carroExistente = findById(id);
        if (carroExistente != null) {
            carroExistente.setNome(carroAtualizado.getNome());
            carroExistente.setMarca(carroAtualizado.getMarca());
            carroExistente.setModelo(carroAtualizado.getModelo());
            carroExistente.setAno(carroAtualizado.getAno());
        }
        return "Modificado com Sucesso";
    }

    public String delete(int id) {
        carros.removeIf(carro -> carro.getId() == id);
        return "Deletado com Sucesso";
    }
}
