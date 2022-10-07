package com.ciclo3.g9.ciclo3.service;


import com.ciclo3.g9.ciclo3.model.Reservation;
import com.ciclo3.g9.ciclo3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation r) {
        if (r.getIdReservation() == null) {
            return reservationRepository.save(r);
        } else {
            Optional<Reservation> raux = reservationRepository.getReservation(r.getIdReservation());
            if (raux.isEmpty()) {
                return reservationRepository.save(r);
            } else {
                return r;
            }
        }
    }

    public Reservation update(Reservation r){
        if(r.getIdReservation()!= null){
            Optional<Reservation> g = reservationRepository.getReservation(r.getIdReservation());
            if(!g.isEmpty()){
                if(r.getStartDate()!=null){
                    g.get().setStartDate(r.getStartDate());
                }
                if(r.getDevolutionDate()!=null){
                    g.get().setDevolutionDate(r.getDevolutionDate());
                }
                if(r.getStatus()!=null){
                    g.get().setStatus(r.getStatus());
                }
                return reservationRepository.save(g.get());
            }
        }
        return r;
    }

    public boolean deleteReservation(int id) {
        Optional<Reservation> r= getReservation(id);
        if(!r.isEmpty()){
            reservationRepository.delete(r.get());
            return true;
        }
        return false;
    }
}
