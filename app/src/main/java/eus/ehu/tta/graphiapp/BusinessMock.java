package eus.ehu.tta.graphiapp;

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
}
