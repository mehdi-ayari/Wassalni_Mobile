/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author jawha
 */
public class voyage {

    private int id_voyage, reservation_id_res;
    private float distance;
    private Date date_voyage;

    public voyage(int id_voyage, int reservation_id_res, float distance, Date date_voyage) {
        this.id_voyage = id_voyage;
        this.reservation_id_res = reservation_id_res;
        this.distance = distance;
        this.date_voyage = date_voyage;
    }

    public voyage(int reservation_id_res, float distance, Date date_voyage) {
        this.reservation_id_res = reservation_id_res;
        this.distance = distance;
        this.date_voyage = date_voyage;
    }

    public int getId_voyage() {
        return id_voyage;
    }

    public int getReservation_id_res() {
        return reservation_id_res;
    }

    public float getDistance() {
        return distance;
    }

    public Date getDate_voyage() {
        return date_voyage;
    }

    public void setId_voyage(int id_voyage) {
        this.id_voyage = id_voyage;
    }

    public void setReservation_id_res(int reservation_id_res) {
        this.reservation_id_res = reservation_id_res;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setDate_voyage(Date date_voyage) {
        this.date_voyage = date_voyage;
    }

    @Override
    public String toString() {
        return "voyage{" + "id_voyage=" + id_voyage + ", reservation_id_res=" + reservation_id_res + ", distance=" + distance + ", date_voyage=" + date_voyage + '}';
    }

}
