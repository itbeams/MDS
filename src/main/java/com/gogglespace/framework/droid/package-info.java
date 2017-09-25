/**
 * Droid Persistence Framework for SQLite
 * - Added support of connection pooling so that system App can avoid from dead lock i.e. 
 * ANR (Application Not Responding error) while database being accessed from multiple sources (user & 
 * secheduled tasks).
 * @author Shahid Nawaz
 * @version 1.0
 */
package com.gogglespace.framework.droid;