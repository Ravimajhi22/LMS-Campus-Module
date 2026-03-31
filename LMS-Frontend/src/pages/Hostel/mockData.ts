import { Building, DoorOpen, Users, ClipboardCheck, AlertTriangle } from 'lucide-react'
import { Hostel, HostelRoom, StudentHostelAllocation, HostelAttendance, HostelComplaint } from '../../types/hostel'
import React from 'react'

export const HOSTEL_TABS = [
  { id: 'hostels', name: 'Hostels', icon: React.createElement(Building, { size: 18 }) },
  { id: 'rooms', name: 'Rooms', icon: React.createElement(DoorOpen, { size: 18 }) },
  { id: 'students', name: 'Students', icon: React.createElement(Users, { size: 18 }) },
  { id: 'attendance', name: 'Attendance', icon: React.createElement(ClipboardCheck, { size: 18 }) },
  { id: 'complaints', name: 'Complaints', icon: React.createElement(AlertTriangle, { size: 18 }) },
]

export const INITIAL_HOSTELS: Hostel[] = [
  { hostelId: 101, hostelName: 'Evergreen Hall', hostelType: 'MEN', totalBlocks: 4, totalRooms: 120, wardenName: 'Dr. Rahul Sharma', contactNumber: '+91 98765 43210', status: 'ACTIVE' },
  { hostelId: 102, hostelName: 'Bluebird Residency', hostelType: 'WOMEN', totalBlocks: 3, totalRooms: 95, wardenName: 'Ms. Anita Patel', contactNumber: '+91 98765 43211', status: 'ACTIVE' },
  { hostelId: 103, hostelName: 'Oak Tower', hostelType: 'MEN', totalBlocks: 5, totalRooms: 150, wardenName: 'Prof. Mark Davis', contactNumber: '+91 98765 43212', status: 'ACTIVE' },
  { hostelId: 104, hostelName: 'Skyview Suites', hostelType: 'COLIVING', totalBlocks: 2, totalRooms: 45, wardenName: 'Mr. David Lee', contactNumber: '+91 98765 43213', status: 'INACTIVE' },
]

export const INITIAL_ROOMS: HostelRoom[] = [
  { roomId: 1, roomNumber: '101A', sharingType: 'SINGLE', currentlyOccupied: 1, status: 'FULL', hostelName: 'Evergreen Hall' },
  { roomId: 2, roomNumber: '204B', sharingType: 'DOUBLE', currentlyOccupied: 1, status: 'PARTIALLY_FILLED', hostelName: 'Bluebird Residency' },
  { roomId: 3, roomNumber: '302C', sharingType: 'TRIPLE', currentlyOccupied: 0, status: 'AVAILABLE', hostelName: 'Oak Tower' },
  { roomId: 4, roomNumber: 'G-12', sharingType: 'QUAD', currentlyOccupied: 4, status: 'FULL', hostelName: 'Evergreen Hall' },
]

export const INITIAL_STUDENTS: StudentHostelAllocation[] = [
  { allocationId: 501, studentId: 1001, studentName: 'Abhay Kumar', studentEmail: 'abhay.k@univ.edu', parentName: 'Rajesh Kumar', parentPhone: '+91 90000 11111', hostelId: 101, roomId: 1, hostelName: 'Evergreen Hall', roomNumber: '101A', joinDate: '2026-01-15', status: 'ACTIVE' },
  { allocationId: 502, studentId: 1002, studentName: 'Priya Singh', studentEmail: 'priya.s@univ.edu', parentName: 'Sanjay Singh', parentPhone: '+91 90000 22222', hostelId: 102, roomId: 2, hostelName: 'Bluebird Residency', roomNumber: '204B', joinDate: '2026-02-01', status: 'ACTIVE' },
  { allocationId: 503, studentId: 1003, studentName: 'John Doe', studentEmail: 'john.d@univ.edu', parentName: 'Robert Doe', parentPhone: '+1 555-0199', hostelId: 103, roomId: 3, hostelName: 'Oak Tower', roomNumber: '302C', joinDate: '2025-11-20', status: 'CHECKED_OUT', leaveDate: '2026-03-30' },
  { allocationId: 504, studentId: 1004, studentName: 'Rohan Mehta', studentEmail: 'rohan.m@univ.edu', parentName: 'Vikram Mehta', parentPhone: '+91 90000 33333', hostelId: 101, roomId: 4, hostelName: 'Evergreen Hall', roomNumber: 'G-12', joinDate: '2026-03-01', status: 'CANCELLED' },
]

export const INITIAL_ATTENDANCE: HostelAttendance[] = [
  { attendanceId: 901, studentId: 1001, studentName: 'Abhay Kumar', roomNumber: '101A', attendanceDate: '2026-03-31', status: 'PRESENT', markedAt: '2026-03-31T20:00:00Z' },
  { attendanceId: 902, studentId: 1002, studentName: 'Priya Singh', roomNumber: '204B', attendanceDate: '2026-03-31', status: 'ABSENT', markedAt: '2026-03-31T20:05:00Z' },
]

export const INITIAL_COMPLAINTS: HostelComplaint[] = [
  { complaintId: 10, studentId: 1001, studentName: 'Abhay Kumar', hostelName: 'Evergreen Hall', roomNumber: '101A', issueCategory: 'ELECTRICAL', priority: 'HIGH', description: 'Power socket in block B not working.', reportedDate: '2026-03-28', status: 'OPEN' },
  { complaintId: 11, studentId: 1002, studentName: 'Priya Singh', hostelName: 'Bluebird Residency', roomNumber: '204B', issueCategory: 'PLUMBING', priority: 'MEDIUM', description: 'Leaking tap in common washroom.', reportedDate: '2026-03-29', status: 'IN_PROGRESS', adminRemarks: 'Technician dispatched.' },
]
