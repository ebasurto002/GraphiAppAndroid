package eus.ehu.tta.graphiapp;

import eus.ehu.tta.graphiapp.Levels.Nivel1;

public class BusinessMock implements Business {

    @Override
    public boolean register(String name, String surname, String password, int userType) {
        return true;
    }

    @Override
    public boolean login(String userName, String password, int userType) {
        if (userName.equals("eb00") && password.equals("1234") && userType == TIPO_ALUMNO)
        {
            return true;
        }
        else if (userName.equals("je00") && password.equals("1234") && userType == TIPO_PROFESOR)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    @Override
    public Nivel1[] getNivel1(Integer pin) {
        return new Nivel1 [] {
            new Nivel1("correcta","incorrecta",1),
            new Nivel1("incorrecta","correcta",2),
            new Nivel1("correcta","incorrecta",1),
            new Nivel1("incorrecta","correcta",2),
            new Nivel1("correcta","incorrecta",1),
            new Nivel1("incorrecta","correcta",2),
            new Nivel1("correcta","incorrecta",1),
            new Nivel1("incorrecta","correcta",2),
            new Nivel1("correcta","incorrecta",1),
            new Nivel1("incorrecta","correcta",2)
        };
    }
}
