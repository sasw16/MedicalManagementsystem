package com.nrifintech.mms.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrifintech.mms.dao.AppointmentDao;
import com.nrifintech.mms.entity.Appointment;
import com.nrifintech.mms.util.DateUtil;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentDao appointmentDao;
	private List<Appointment> list;

	public int verifyAppointment(Appointment a) {
		list = this.appointmentDao.fetchAppointmentList();
		for (Appointment app : list) {
			if (app.getStatus().equals("Inactive") && a.getDoc_id() == app.getDoc_id()
					&& a.getDate().equals(app.getDate()) && a.getSlot().equals(app.getSlot())) {
				for (Appointment ap : list) {
					if (ap.getP_id() == a.getP_id() && ap.getStatus().equals("Active")
							&& ap.getDate().equals(a.getDate()) && ap.getSlot().equals(a.getSlot()))
						return 0;
				}

				for (Appointment ap : list) {
					if (ap.getDoc_id() == a.getDoc_id() && ap.getStatus().equals("Active")
							&& ap.getDate().equals(a.getDate()) && ap.getSlot().equals(a.getSlot()))
						return 2;
				}

				this.appointmentDao.registerAppointment(a);
				return 1;
			}
			if (a.getDate().equals(app.getDate()) && a.getSlot().equals(app.getSlot())
					&& (a.getP_id() == app.getP_id())) {
				return 0;
			}
			if (a.getDate().equals(app.getDate()) && a.getSlot().equals(app.getSlot())
					&& (a.getDoc_id() == app.getDoc_id())) {
				return 2;
			}
		}
		this.appointmentDao.registerAppointment(a);
		return 1;
	}

//	public int verifyAppointment(Appointment a) {
//		list = this.appointmentDao.fetchAppointmentList();
//		for (Appointment app : list) {
//			if (app.getStatus().equals("Inactive") && a.getDoc_id() == app.getDoc_id() && app.getCancel().equals("Cancelled By Patient")
//					&& a.getDate().equals(app.getDate()) && a.getSlot().equals(app.getSlot())) {
//				for (Appointment ap : list) {
//					if (ap.getP_id() == a.getP_id() && ap.getStatus().equals("Active")
//							&& ap.getDate().equals(a.getDate()) && ap.getSlot().equals(a.getSlot()))
//						return 0;
//				}
//
//				this.appointmentDao.registerAppointment(a);
//				return 1;
//			}
//			else if (a.getDate().equals(app.getDate()) && a.getSlot().equals(app.getSlot())
//					&& (a.getP_id() == app.getP_id()) && app.getStatus().equals("Active")) {
//				System.out.println("1");
//				return 0;
//			}
//			else if (a.getDate().equals(app.getDate()) && a.getSlot().equals(app.getSlot())
//					&& (a.getDoc_id() == app.getDoc_id()) &&  app.getStatus().equals("Active")) {
//				System.out.println("2");
//				return 2;
//			}
//		}
//		System.out.println("3");
//		this.appointmentDao.registerAppointment(a);
//		return 1;
//	}

	public List<Appointment> getAppointmentByPId(Integer id) {
		list = this.appointmentDao.fetchAppointmentList();
		LocalDate ld = LocalDate.now();
		List<Appointment> pAppointments = new ArrayList<>();
		for (Appointment ap : list) {
			if (ap.getP_id() == id && ap.getStatus().equals("Active")) {
				LocalDate d = LocalDate.parse(ap.getDate());
				long days = ChronoUnit.DAYS.between(ld, d);
				if (days == 0) {
					if (checkTiming(ap.getSlot()))
						this.appointmentDao.cancelAppointment(ap);

				} else if (days >= 0)
					pAppointments.add(ap);
				else {
					this.appointmentDao.cancelAppointment(ap);
				}

			}
		}
		Collections.sort(pAppointments, Comparator.comparing(Appointment::getDate).thenComparing(Appointment::getSlot));

		return pAppointments;
	}

	public List<Appointment> getAppointmentReview(Integer id) {
		list = this.appointmentDao.fetchAppointmentList();

		List<Appointment> pReviews = new ArrayList<>();
		for (Appointment ap : list) {
			if (ap.getP_id() == id && ap.getStatus().equals("Inactive") && ap.getCancel().equals("Not Cancelled")) {
				pReviews.add(ap);
			}
		}
		Collections.sort(pReviews,
				Comparator.comparing(Appointment::getDate).thenComparing(Appointment::getSlot).reversed());

		return pReviews;
	}

	public List<Appointment> getAppointmentByDId(Integer id) {
		list = this.appointmentDao.fetchAppointmentList();
		LocalDate ld = LocalDate.now();
		List<Appointment> dAppointments = new ArrayList<>();
		for (Appointment ap : list) {
			if (ap.getDoc_id() == id && ap.getStatus().equals("Active")) {
				LocalDate d = LocalDate.parse(ap.getDate());
				long days = ChronoUnit.DAYS.between(ld, d);
				if (days == 0) {
					if (checkTiming(ap.getSlot()))
						this.appointmentDao.cancelAppointment(ap);
				} else if (days >= 0) {
					dAppointments.add(ap);
				} else {
					this.appointmentDao.cancelAppointment(ap);
				}
			}
		}
		Collections.sort(dAppointments, Comparator.comparing(Appointment::getDate).thenComparing(Appointment::getSlot));

		return dAppointments;
	}

	public boolean checkForCancel(Integer id) {
		Appointment a = this.appointmentDao.getAppointmentById(id);
		LocalDate ld = LocalDate.now();
		System.out.println(ld);
		LocalDate d = LocalDate.parse(a.getDate());
		System.out.println(d);

		long days = ChronoUnit.DAYS.between(ld, d);
		if (days <= 1)
			return false;
		this.appointmentDao.cancelAppointmentTrial(a);
		return true;
	}

//	public boolean checkForCancel(Integer id) {
//		Appointment a = this.appointmentDao.getAppointmentById(id);
//		LocalDate ld = LocalDate.now();
//		System.out.println(ld);
//		LocalDate d = LocalDate.parse(a.getDate());
//		System.out.println(d);
//
//		long days = ChronoUnit.DAYS.between(ld, d);
//		if (days <= 1)
//			return false;
//		else {
//			this.appointmentDao.cancelAppointment(a);
//			return true;
//
//		}
//	}

	public boolean checkTiming(String slot) {

		LocalTime time = LocalTime.now();
		if (slot.equals("10.00")) {
			if (time.getHour() - 10 >= 0 && time.getMinute() >= 0)
				return true;
		} else if (slot.equals("10.15")) {
			if (time.getHour() - 10 >= 0 && time.getMinute() >= 15)
				return true;
		} else if (slot.equals("10.30")) {
			if (time.getHour() - 10 >= 0 && time.getMinute() >= 30)
				return true;
		} else if (slot.equals("10.45")) {
			if (time.getHour() - 10 >= 0 && time.getMinute() >= 45)
				return true;
		} else if (slot.equals("11.00")) {
			if (time.getHour() - 11 >= 0 && time.getMinute() >= 0)
				return true;
		} else if (slot.equals("11.15")) {
			if (time.getHour() - 11 >= 0 && time.getMinute() >= 15)
				return true;
		} else if (slot.equals("11.30")) {
			if (time.getHour() - 11 >= 0 && time.getMinute() >= 30)
				return true;
		} else if (slot.equals("11.45")) {
			if (time.getHour() - 11 >= 0 && time.getMinute() >= 45)
				return true;
		} else if (slot.equals("12.00")) {
			if (time.getHour() - 12 >= 0 && time.getMinute() >= 0)
				return true;
		} else if (slot.equals("12.15")) {
			if (time.getHour() - 12 >= 0 && time.getMinute() >= 15)
				return true;
		} else if (slot.equals("12.30")) {
			if (time.getHour() - 12 >= 0 && time.getMinute() >= 30)
				return true;
		} else if (slot.equals("12.45")) {
			if (time.getHour() - 12 >= 0 && time.getMinute() >= 45)
				return true;
		} else if (slot.equals("13.00")) {
			if (time.getHour() - 13 >= 0 && time.getMinute() >= 0)
				return true;
		} else if (slot.equals("13.15")) {
			if (time.getHour() - 13 >= 0 && time.getMinute() >= 15)
				return true;
		} else if (slot.equals("13.30")) {
			if (time.getHour() - 13 >= 0 && time.getMinute() >= 30)
				return true;
		} else if (slot.equals("13.45")) {
			if (time.getHour() - 13 >= 0 && time.getMinute() >= 45)
				return true;
		} else if (slot.equals("14.00")) {
			if (time.getHour() - 14 >= 0 && time.getMinute() >= 0)
				return true;
		} else if (slot.equals("14.15")) {
			if (time.getHour() - 14 >= 0 && time.getMinute() >= 15)
				return true;
		} else if (slot.equals("14.30")) {
			if (time.getHour() - 14 >= 0 && time.getMinute() >= 30)
				return true;
		} else if (slot.equals("14.45")) {
			if (time.getHour() - 14 >= 0 && time.getMinute() >= 45)
				return true;
		} else if (slot.equals("15.00")) {
			if (time.getHour() - 15 >= 0 && time.getMinute() >= 0)
				return true;
		} else if (slot.equals("15.15")) {
			if (time.getHour() - 15 >= 0 && time.getMinute() >= 15)
				return true;
		} else if (slot.equals("15.30")) {
			if (time.getHour() - 15 >= 0 && time.getMinute() >= 30)
				return true;
		} else if (slot.equals("15.45")) {
			if (time.getHour() - 15 >= 0 && time.getMinute() >= 45)
				return true;
		} else if (slot.equals("16.00")) {
			if (time.getHour() - 16 >= 0 && time.getMinute() >= 0)
				return true;
		} else if (slot.equals("16.15")) {
			if (time.getHour() - 16 >= 0 && time.getMinute() >= 15)
				return true;
		} else if (slot.equals("16.30")) {
			if (time.getHour() - 16 >= 0 && time.getMinute() >= 30)
				return true;
		} else if (slot.equals("16.45")) {
			if (time.getHour() - 16 >= 0 && time.getMinute() >= 45)
				return true;
		}
		return false;
	}

	public List<Appointment> getWeeklyAppointmentsByDocID(Integer id) {
		list = this.appointmentDao.fetchAppointmentList();
		List<Appointment> filteredAppointments = new ArrayList<>();
		DateUtil dateutil = new DateUtil();

		for (Appointment ap : list) {
			LocalDate d = LocalDate.parse(ap.getDate());
			if (ap.getDoc_id() == id && dateutil.isDateInCurrentWeek(d))
				filteredAppointments.add(ap);
		}

		return filteredAppointments;
	}

	public List<Appointment> getMonthlyAppointmentsByDocID(Integer id) {
		list = this.appointmentDao.fetchAppointmentList();
		List<Appointment> filteredAppointments = new ArrayList<>();
		DateUtil dateutil = new DateUtil();

		for (Appointment ap : list) {
			LocalDate d = LocalDate.parse(ap.getDate());
			if (ap.getDoc_id() == id && dateutil.isDateInCurrentMonth(d))
				filteredAppointments.add(ap);
		}

		Collections.sort(filteredAppointments,
				Comparator.comparing(Appointment::getDate).thenComparing(Appointment::getSlot));

		return filteredAppointments;
	}

	public List<String> slotList(Integer id, String date) {

		list = this.appointmentDao.fetchAppointmentList();
		List<String> notAvailable = new ArrayList<>();
		for (Appointment a : list) {
			if (a.getDoc_id() == id && a.getDate().equals(date) && a.getStatus().equals("Active"))
				notAvailable.add(a.getSlot());
		}
		System.out.println(notAvailable);
		return notAvailable;
	}

	public boolean insertAppointment(Appointment a) {
		list = this.appointmentDao.fetchAppointmentList();
		for (Appointment app : list) {
			if (app.getDate().equals(a.getDate()) && app.getP_id() == a.getP_id() && app.getSlot().equals(a.getSlot())
					&& app.getStatus().equals("Active")) {
				return false;
			}
		}
		this.appointmentDao.registerAppointment(a);
		return true;
	}

	public void updateReview(Appointment ap, Integer review) {
		this.appointmentDao.updateReview(ap, review);
	}

}
