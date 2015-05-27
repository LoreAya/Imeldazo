/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entities.Relationship;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jesus
 */
@Stateless
public class RelationshipFacade extends AbstractFacade<Relationship> {
    @PersistenceContext(unitName = "AlbumPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelationshipFacade() {
        super(Relationship.class);
    }
    
}
