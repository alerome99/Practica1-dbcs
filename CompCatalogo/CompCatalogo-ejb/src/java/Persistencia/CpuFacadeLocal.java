/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Cpu;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Propietario
 */
@Local
public interface CpuFacadeLocal {

    void create(Cpu cpu);

    void edit(Cpu cpu);

    void remove(Cpu cpu);

    Cpu find(Object id);

    List<Cpu> findAll();

    List<Cpu> findRange(int[] range);

    int count();
    
}
