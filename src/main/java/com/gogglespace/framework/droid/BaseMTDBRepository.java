package com.gogglespace.framework.droid;import java.util.List;import android.content.Context;
/**
 *  - Droid Persistence Framework Base DB repository 
 * <br />A generic repository class for db adapters
 * @author Shahid Nawaz on 2/24/2016.
 * @version 1.0
 */
abstract class BaseMTDBRepository<T>  extends BaseMTDBManager {

    // T stands for "Type"
    T type;
    public BaseMTDBRepository(Context context, T o) {
        super(context);
        type = o;    }
    /**
    * Get a list of entity objects of specified return type
    *
    * @return List<T> - where T is of type entity caller is assigned to
    *
    * */
    @SuppressWarnings("hiding")
	public <T> List<T> getAll(){
        return getAll(null);
    }

    /**
     * Get entity by given criteria
     * @param criteria - criteria specified
     * @see Criteria
     * 
     * @return T - is of type entity caller is assigned to
     * */
    @SuppressWarnings("hiding")
	public <T> T get(Criteria criteria){
    	List<T> list = getAll(criteria);
    	if (!ObjectUtils.isNullOrEmpty(list)) {
    		return list.get(0);
    	}
    	else {
    		return null;
    	}
    }
    
    /**
     * Get a list of entity objects of specified return type matching given criteria     * @param criteria - criteria to restrict fetch list     * @see Criteria
     * @return List<T> - where T is of type entity caller is assigned to
     * */
    @SuppressWarnings({ "hiding" })	abstract <T> List<T> getAll(Criteria criteria);    
    /**
     * Retrieve an entity by Id (pk)
     *
     * @param id - pk
     *
     * @return T - entity of caller type
     * */
	@SuppressWarnings("hiding")	abstract <T> T get(long id);
       

    /**
     * Create / update an entity object
     * @param obj - an entity to be saved / update in database
     * @return object of type T 
     * */
	@SuppressWarnings("hiding")	abstract <T> T saveOrUpdate(T obj);
    /**     * Save or update entities     *     * @param objList - list of entities to be inserted / updated     *     * @return List<T> - list of entities after updating      * */    @SuppressWarnings({ "hiding"})	abstract <T> List<T> saveOrUpdate(List<T> objList);        /**     * Delete All records from table     * @return int - returns -1 for deletion failed and positive value 1 otherwise     * */	public int deleteAll() {    	return delete(null);    }
    /**
     * Delete by provided criteria
     *
     * @param criteria - delete entity/entities based on criteria
     * @return INT - returns -1 for deletion failed and positive value 1 otherwise
     * */
	abstract int delete(Criteria criteria);

    /**
     * Delete passed entity
     *
     * @param obj - an entity to be deleted
     * @return int - returns -1 for deletion failed and positive value 1 otherwise
     * */
	@SuppressWarnings("hiding")	abstract <T> int delete(T obj);

    /**
     * Update an entity
     *
     * @param obj
     *
     * @return T
     * */
    @SuppressWarnings("hiding")
	public <T> T update(T obj) {
        return saveOrUpdate(obj);
    }        /**     * Based on criteria determines if there are records available or not     * @param criteria - criteria      * @return boolean - true / false     * */
	abstract boolean hasRecords(Criteria criteria);

    /**
     * Reset sequence of an entity as provided
     * @param obj - an entity to be saved / update index of     * @param sequence 
     * */
	abstract void resetSequence(T obj, long sequence);}
