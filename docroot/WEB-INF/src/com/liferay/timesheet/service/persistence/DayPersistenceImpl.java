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
import com.liferay.portal.kernel.dao.orm.QueryPos;
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
import com.liferay.portal.kernel.util.StringPool;
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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_T = new FinderPath(DayModelImpl.ENTITY_CACHE_ENABLED,
			DayModelImpl.FINDER_CACHE_ENABLED, DayImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_T",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T = new FinderPath(DayModelImpl.ENTITY_CACHE_ENABLED,
			DayModelImpl.FINDER_CACHE_ENABLED, DayImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_T",
			new String[] { Long.class.getName(), Integer.class.getName() },
			DayModelImpl.COMPANYID_COLUMN_BITMASK |
			DayModelImpl.TYPE_COLUMN_BITMASK |
			DayModelImpl.DATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_T = new FinderPath(DayModelImpl.ENTITY_CACHE_ENABLED,
			DayModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_T",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the daies where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the matching daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Day> findByC_T(long companyId, int type)
		throws SystemException {
		return findByC_T(companyId, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the daies where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.DayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of daies
	 * @param end the upper bound of the range of daies (not inclusive)
	 * @return the range of matching daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Day> findByC_T(long companyId, int type, int start, int end)
		throws SystemException {
		return findByC_T(companyId, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the daies where companyId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.timesheet.model.impl.DayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param start the lower bound of the range of daies
	 * @param end the upper bound of the range of daies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Day> findByC_T(long companyId, int type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T;
			finderArgs = new Object[] { companyId, type };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_T;
			finderArgs = new Object[] {
					companyId, type,
					
					start, end, orderByComparator
				};
		}

		List<Day> list = (List<Day>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Day day : list) {
				if ((companyId != day.getCompanyId()) ||
						(type != day.getType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DAY_WHERE);

			query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_T_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DayModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(type);

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
	 * Returns the first day in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching day
	 * @throws com.liferay.timesheet.NoSuchDayException if a matching day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Day findByC_T_First(long companyId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchDayException, SystemException {
		Day day = fetchByC_T_First(companyId, type, orderByComparator);

		if (day != null) {
			return day;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDayException(msg.toString());
	}

	/**
	 * Returns the first day in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching day, or <code>null</code> if a matching day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Day fetchByC_T_First(long companyId, int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<Day> list = findByC_T(companyId, type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last day in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching day
	 * @throws com.liferay.timesheet.NoSuchDayException if a matching day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Day findByC_T_Last(long companyId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchDayException, SystemException {
		Day day = fetchByC_T_Last(companyId, type, orderByComparator);

		if (day != null) {
			return day;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDayException(msg.toString());
	}

	/**
	 * Returns the last day in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching day, or <code>null</code> if a matching day could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Day fetchByC_T_Last(long companyId, int type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_T(companyId, type);

		if (count == 0) {
			return null;
		}

		List<Day> list = findByC_T(companyId, type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the daies before and after the current day in the ordered set where companyId = &#63; and type = &#63;.
	 *
	 * @param dayId the primary key of the current day
	 * @param companyId the company ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next day
	 * @throws com.liferay.timesheet.NoSuchDayException if a day with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Day[] findByC_T_PrevAndNext(long dayId, long companyId, int type,
		OrderByComparator orderByComparator)
		throws NoSuchDayException, SystemException {
		Day day = findByPrimaryKey(dayId);

		Session session = null;

		try {
			session = openSession();

			Day[] array = new DayImpl[3];

			array[0] = getByC_T_PrevAndNext(session, day, companyId, type,
					orderByComparator, true);

			array[1] = day;

			array[2] = getByC_T_PrevAndNext(session, day, companyId, type,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Day getByC_T_PrevAndNext(Session session, Day day,
		long companyId, int type, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DAY_WHERE);

		query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_T_TYPE_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(DayModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(day);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Day> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the daies where companyId = &#63; and type = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_T(long companyId, int type) throws SystemException {
		for (Day day : findByC_T(companyId, type, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(day);
		}
	}

	/**
	 * Returns the number of daies where companyId = &#63; and type = &#63;.
	 *
	 * @param companyId the company ID
	 * @param type the type
	 * @return the number of matching daies
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_T(long companyId, int type) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_T;

		Object[] finderArgs = new Object[] { companyId, type };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DAY_WHERE);

			query.append(_FINDER_COLUMN_C_T_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_T_TYPE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(type);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_T_COMPANYID_2 = "day.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_T_TYPE_2 = "day.type = ?";

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

		DayModelImpl dayModelImpl = (DayModelImpl)day;

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

		if (isNew || !DayModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((dayModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dayModelImpl.getOriginalCompanyId(),
						dayModelImpl.getOriginalType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T,
					args);

				args = new Object[] {
						dayModelImpl.getCompanyId(), dayModelImpl.getType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_T, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_T,
					args);
			}
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
	private static final String _SQL_SELECT_DAY_WHERE = "SELECT day FROM Day day WHERE ";
	private static final String _SQL_COUNT_DAY = "SELECT COUNT(day) FROM Day day";
	private static final String _SQL_COUNT_DAY_WHERE = "SELECT COUNT(day) FROM Day day WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "day.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Day exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Day exists with the key {";
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