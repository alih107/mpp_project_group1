package back.service;

import back.dataaccess.DataAccess;
import back.dataaccess.DataAccessFacade;

public class BaseService {

    protected final DataAccess dataAccess = DataAccessFacade.getInstance();
}
