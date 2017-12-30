package eus.ehu.tta.graphiapp;

import eus.ehu.tta.graphiapp.Levels.Nivel1;
import eus.ehu.tta.graphiapp.Levels.Nivel2;

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

    public Nivel2[] getNivel2(Integer pin) {
        return new Nivel2[] {
                new Nivel2("Telefono",4,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/telefono.mp3"),
                new Nivel2("camaleon",7,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/camaleon.mp3"),
                new Nivel2("cancer",2,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/cancer.mp3"),
                new Nivel2("pais",3,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/pais.mp3"),
                new Nivel2("matematicas",6,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/matematicas.mp3"),
                new Nivel2("adios",4,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/adios.mp3"),
                new Nivel2("trebol",3,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/trebol.mp3"),
                new Nivel2("bisturi",7,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/bisturi.mp3"),
                new Nivel2("nitido",2,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/nitido.mp3"),
                new Nivel2("capitan",6,"http://u017633.ehu.eus:28080/GraphiAppServer/audio/capitan.mp3")
        };
    }
}
