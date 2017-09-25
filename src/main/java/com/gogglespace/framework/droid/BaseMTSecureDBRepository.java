package com.gogglespace.framework.droid;
/**
 *  - Droid Persistence Framework Base DB repository for encrypted db <br />
 * A generic repository class for db adapters
 * @author Shahid Nawaz on 2/24/2016.
 * @version 1.0
 */
abstract class BaseMTSecureDBRepository<T>  extends BaseMTSecureDBManager {

    // T stands for "Type"
    T type;
    BaseMTSecureDBRepository(Context context, T o) {
        super(context);
        type = o;
    /**
    * Get a list of entity objects of specified return type
    * @return List<T> - where T is of type entity caller is assigned to
    *
    * */
    @SuppressWarnings("hiding")
	<T> List<T> getAll(){
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
    	List<T> list = getAll(criteria);
    	if (!ObjectUtils.isNullOrEmpty(list)) {
    		return list.get(0);
    	}
    	else {
    		return null;
    	}
    }
    
    /**
     * Get a list of entity objects of specified return type matching given criteria
     * @return List<T> - where T is of type entity caller is assigned to
     * */
    @SuppressWarnings({ "hiding" })
    /**
     * Retrieve an entity by Id (pk)
     *
     * @param id - pk
     *
     * @return T - entity of caller type
     * */
	@SuppressWarnings("hiding")
       

    /**
     * Create / update an entity object
     * @param obj - an entity to be saved / update in database
     * @return object of type T 
     * */
	@SuppressWarnings("hiding")

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
	@SuppressWarnings("hiding")

    /**
     * Update an entity
     *
     * @param obj
     *
     * @return T
     * */
    @SuppressWarnings("hiding")
	<T> T update(T obj) {
        return saveOrUpdate(obj);
    }
	abstract boolean hasRecords(Criteria criteria);

    /**
     * Reset sequence of an entity as provided
     * @param obj - an entity to be saved / update index of
     * */
	abstract void resetSequence(T obj, long sequence);