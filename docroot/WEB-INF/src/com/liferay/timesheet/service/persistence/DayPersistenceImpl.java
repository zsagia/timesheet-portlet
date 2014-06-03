/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.timesheet.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.timesheet.NoSuchDayException;
import com.liferay.timesheet.model.Day;
import com.liferay.timesheet.model.impl.DayImpl;
import com.liferay.timesheet.model.impl.DayModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the day service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see DayPersistence
 * @see DayUtil
 * @generated
 */
public class DayPersistenceImpl extends BasePersistenceImpl<Day>
	implements DayPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DayUtil} to access the day persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DayImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DayModelImpl.ENTITY_CACHE_ENABLED,
			DayModelImpl.FINDER_CACHE_ENABLED, DayImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DayModelImpl.ENTITY_CACHE_ENABLED,
			DayModelImpl.FINDER_CACHE_ENABLED, DayImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DayModelImpl.ENTITY_CACHE_ENABLED,
			DayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public DayPersistenceImpl() {
		setModelClass(Day.class);
	}

	/**
	 * Caches the day in the entity cache if it is enabled.
	 *
	 * @param day the day
	 */
	@Override
	public void cacheResult(Day day) {
		EntityCacheUtil.putResult(DayModelImpl.ENTITY_CACHE_ENABLED,
			DayImpl.class, day.getPrimaryKey(), day);

		day.resetOriginalValues();
	}

	/**
	 * Caches the daies in the entity cache if it is enabled.
	 *
	 * @param daies the daies
	 */
	@Override
	public void cacheResult(List<Day> daies) {
		for (Day day : daies) {
			if (EntityCacheUtil.getResult(DayModelImpl.ENTITY_CACHE_ENABLED,
						DayImpl.class, day.getPrimaryKey()) == null) {
				cacheResult(day);
			}
			else {
				day.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all daies.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DayImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DayImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the day.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Day day) {
		EntityCacheUtil.removeResult(DayModelImpl.ENTITY_CACHE_ENABLED,
			DayImpl.class, day.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Day> daies) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Day day : daies) {
			EntityCacheUtil.removeResult(DayModelImpl.ENTITY_CACHE_ENABLED,
				DayImpl.class, day.getPrimaryKey());
		}
	}

	/**
	 * Creates a new day with the primary key. Does not add the day to the database.
	 *
	 * @param dayId the primary key for the new day
	 * @return the new day
	 */
	@Override
	public Day create(long dayId) {
		Day day = new DayImpl();

		day.setNew(true);
		day.setPrimaryKey(dayId);

		return day;
	}

	/**
	 * Removes the day with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dayId the primary key of the day
	 * @return the day that was removed
	 * @throws com.liferay.timesheet.NoSuchDayException if a day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Day remove(long dayId) throws NoSuchDayException, SystemException {
		return remove((Serializable)dayId);
	}

	/**
	 * Removes the day with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the day
	 * @return the day that was removed
	 * @throws com.liferay.timesheet.NoSuchDayException if a day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Day remove(Serializable primaryKey)
		throws NoSuchDayException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Day day = (Day)session.get(DayImpl.class, primaryKey);

			if (day == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDayException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(day);
		}
		catch (NoSuchDayException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Day removeImpl(Day day) throws SystemException {
		day = toUnwrappedModel(day);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(day)) {
				day = (Day)session.get(DayImpl.class, day.getPrimaryKeyObj());
			}

			if (day != null) {
				session.delete(day);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (day != null) {
			clearCache(day);
		}

		return day;
	}

	@Override
	public Day updateImpl(com.liferay.timesheet.model.Day day)
		throws SystemException {
		day = toUnwrappedModel(day);

		boolean isNew = day.isNew();

		Session session = null;

		try {
			session = openSession();

			if (day.isNew()) {
				session.save(day);

				day.setNew(false);
			}
			else {
				session.merge(day);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(DayModelImpl.ENTITY_CACHE_ENABLED,
			DayImpl.class, day.getPrimaryKey(), day);

		return day;
	}

	protected Day toUnwrappedModel(Day day) {
		if (day instanceof DayImpl) {
			return day;
		}

		DayImpl dayImpl = new DayImpl();

		dayImpl.setNew(day.isNew());
		dayImpl.setPrimaryKey(day.getPrimaryKey());

		dayImpl.setDayId(day.getDayId());
		dayImpl.setGroupId(day.getGroupId());
		dayImpl.setCompanyId(day.getCompanyId());
		dayImpl.setUserId(day.getUserId());
		dayImpl.setUserName(day.getUserName());
		dayImpl.setCreateDate(day.getCreateDate());
		dayImpl.setModifiedDate(day.getModifiedDate());
		dayImpl.setDate(day.getDate());
		dayImpl.setType(day.getType());

		return dayImpl;
	}

	/**
	 * Returns the day with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the day
	 * @return the day
	 * @throws com.liferay.timesheet.NoSuchDayException if a day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Day findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDayException, SystemException {
		Day day = fetchByPrimaryKey(primaryKey);

		if (day == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDayException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return day;
	}

	/**
	 * Returns the day with the primary key or throws a {@link com.liferay.timesheet.NoSuchDayException} if it could not be found.
	 *
	 * @param dayId the primary key of the day
	 * @return the day
	 * @throws com.liferay.timesheet.NoSuchDayException if a day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Day findByPrimaryKey(long dayId)
		throws NoSuchDayException, SystemException {
		return findByPrimaryKey((Serializable)dayId);
	}

	/**
	 * Returns the day with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the day
	 * @return the day, or <code>null</code> if a day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Day fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Day day = (Day)EntityCacheUtil.getResult(DayModelImpl.ENTITY_CACHE_ENABLED,
				DayImpl.class, primaryKey);

		if (day == _nullDay) {
			return null;
		}

		if (day == null) {
			Session session = null;

			try {
				session = openSession();

				day = (Day)session.get(DayImpl.class, primaryKey);

				if (day != null) {
					cacheResult(day);
				}
				else {
					EntityCacheUtil.putResult(DayModelImpl.ENTITY_CACHE_ENABLED,
						DayImpl.class, primaryKey, _nullDay);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(DayModelImpl.ENTITY_CACHE_ENABLED,
					DayImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return day;
	}

	/**
	 * Returns the day with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dayId the primary key of the day
	 * @return the day, or <code>null</code> if a day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Day fetchByPrimaryKey(long dayId) throws SystemException {
		return fetchByPrimaryKey((Serializable)dayId);
	}

	/**
	 * Returns all the daies.
	 *
	 * @return the daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Day> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the daies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.DayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of daies
	 * @param end the upper bound of the range of daies (not inclusive)
	 * @return the range of daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Day> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the daies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.DayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of daies
	 * @param end the upper bound of the range of daies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Day> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Day> list = (List<Day>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DAY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DAY;

				if (pagination) {
					sql = sql.concat(DayModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Day>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Day>(list);
				}
				else {
					list = (List<Day>)QueryUtil.list(q, getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the daies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Day day : findAll()) {
			remove(day);
		}
	}

	/**
	 * Returns the number of daies.
	 *
	 * @return the number of daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DAY);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the day persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.timesheet.model.Day")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Day>> listenersList = new ArrayList<ModelListener<Day>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Day>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(DayImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_DAY = "SELECT day FROM Day day";
	private static final String _SQL_COUNT_DAY = "SELECT COUNT(day) FROM Day day";
	private static final String _ORDER_BY_ENTITY_ALIAS = "day.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Day exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(DayPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"date", "type"
			});
	private static Day _nullDay = new DayImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Day> toCacheModel() {
				return _nullDayCacheModel;
			}
		};

	private static CacheModel<Day> _nullDayCacheModel = new CacheModel<Day>() {
			@Override
			public Day toEntityModel() {
				return _nullDay;
			}
		};
}