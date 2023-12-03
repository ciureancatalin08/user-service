package com.example.bugservice.controller.datamodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public class BaseDataModel<T extends Serializable> implements Serializable {

    private T id;

    public BaseDataModel() {
    }

}
