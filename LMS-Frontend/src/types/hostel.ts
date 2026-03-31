export type HostelType = 'MEN' | 'WOMEN' | 'COLIVING';
export type HostelStatus = 'ACTIVE' | 'INACTIVE';
export type SharingType = 'SINGLE' | 'DOUBLE' | 'TRIPLE' | 'QUAD';
export type RoomStatus = 'AVAILABLE' | 'PARTIALLY_FILLED' | 'FULL';
export type AllocationStatus = 'ACTIVE' | 'CHECKED_OUT' | 'CANCELLED';
export type AttendanceStatus = 'PRESENT' | 'ABSENT';
export type IssueCategory = 'PLUMBING' | 'ELECTRICAL' | 'CLEANING' | 'INTERNET' | 'FURNITURE' | 'OTHER';
export type PriorityLevel = 'LOW' | 'MEDIUM' | 'HIGH';
export type ComplaintStatus = 'OPEN' | 'IN_PROGRESS' | 'RESOLVED' | 'CLOSED';

export interface Hostel {
  hostelId: number;
  hostelName: string;
  hostelType: HostelType;
  totalBlocks: number;
  totalRooms: number;
  wardenName: string;
  contactNumber: string;
  status: HostelStatus;
}

export interface HostelRoom {
  roomId: number;
  roomNumber: string;
  sharingType: SharingType;
  status: RoomStatus;
  currentlyOccupied: number;
  hostelName?: string; // For frontend display efficiency
}

export interface StudentHostelAllocation {
  allocationId: number;
  studentId: number;
  studentName: string;
  studentEmail: string;
  parentName: string;
  parentPhone: string;
  hostelId: number;
  roomId: number;
  hostelName: string;
  roomNumber: string;
  joinDate: string;
  leaveDate?: string;
  status: AllocationStatus;
}

export interface HostelAttendance {
  attendanceId: number;
  studentId: number;
  studentName: string;
  roomNumber: string;
  attendanceDate: string;
  status: AttendanceStatus;
  markedAt: string;
}

export interface HostelComplaint {
  complaintId: number;
  studentId: number;
  studentName: string;
  hostelName: string;
  roomNumber: string;
  issueCategory: IssueCategory;
  priority: PriorityLevel;
  description: string;
  reportedDate: string;
  status: ComplaintStatus;
  adminRemarks?: string;
}
