package back.service;

import back.repo.dataaccess.DataAccess;
import back.repo.dataaccess.DataAccessFacade;

public class BaseService {

    protected final DataAccess dataAccess = DataAccessFacade.getInstance();
}
