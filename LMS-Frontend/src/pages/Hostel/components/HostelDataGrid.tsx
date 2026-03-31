import { motion } from 'framer-motion'
import { Building, DoorOpen, UserCheck, Phone } from 'lucide-react'
import { Hostel, HostelRoom, StudentHostelAllocation, HostelAttendance, HostelComplaint } from '../../../types/hostel'

interface HostelDataGridProps {
  activeTab: string
  hostels: Hostel[]
  rooms: HostelRoom[]
  students: StudentHostelAllocation[]
  attendance: HostelAttendance[]
  complaints: HostelComplaint[]
}

const HostelDataGrid = ({ activeTab, hostels, rooms, students, attendance, complaints }: HostelDataGridProps) => {
  const tableVariants = {
    hidden: { y: 15, opacity: 0 },
    visible: { y: 0, opacity: 1, transition: { duration: 0.4, ease: "easeOut" as any } }
  }

  const getSharingCapacity = (type: string) => {
    switch(type) {
      case 'SINGLE': return 1;
      case 'DOUBLE': return 2;
      case 'TRIPLE': return 3;
      case 'QUAD': return 4;
      default: return 1;
    }
  }

  return (
    <motion.div
      key={activeTab}
      variants={tableVariants}
      initial="hidden"
      animate="visible"
      exit={{ opacity: 0, y: -10 }}
      className="bg-white overflow-hidden border border-slate-100 rounded-[2.5rem] shadow-xl"
    >
      <div className="overflow-x-auto">
        <table className="w-full text-left">
          <thead className="bg-slate-50 divide-y divide-slate-100">
            <tr>
              {activeTab === 'hostels' && (
                <>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-400">ID</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Hostel Identity</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Category</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Capacity Matrix</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Primary Warden</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[10px] text-slate-900 border-l border-slate-100">Operational Status</th>
                </>
              )}
              {activeTab === 'rooms' && (
                <>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-400">UUID</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Vector ID</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Parent Hall</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Slot Configuration</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Occupancy Sync</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Node Status</th>
                </>
              )}
              {activeTab === 'students' && (
                <>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-400">Ref ID</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">User Profile</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Guardian Protocol</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Assigned Sector</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Entry Timestamp</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Registry Bit</th>
                </>
              )}
              {activeTab === 'attendance' && (
                <>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-400">Logic ID</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Subject</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Target Node</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Marked At</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Signal Status</th>
                </>
              )}
              {activeTab === 'complaints' && (
                <>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-400">Ticket ID</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Issue Category</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Origin Node</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Priority lvl</th>
                  <th className="px-10 py-7 font-black uppercase tracking-[0.2em] text-[9px] text-slate-900 border-l border-slate-100">Ticket Status</th>
                </>
              )}
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-100/60">
            {activeTab === 'hostels' && hostels.map((hostel, idx) => (
              <tr key={idx} className="hover:bg-slate-50 transition-all group">
                <td className="px-10 py-6 font-mono text-[10px] text-slate-400 font-bold">#{hostel.hostelId}</td>
                <td className="px-10 py-6 border-l border-slate-100">
                  <div className="flex items-center gap-4">
                    <div className="h-9 w-9 rounded-xl bg-primary-50 flex items-center justify-center text-primary-600 border border-primary-100">
                       <Building size={16} />
                    </div>
                    <span className="text-slate-900 text-[13px] font-black uppercase tracking-tight">{hostel.hostelName}</span>
                  </div>
                </td>
                <td className="px-10 py-6 border-l border-slate-100">
                  <span className="text-[10px] font-black text-slate-400 uppercase tracking-widest">{hostel.hostelType}</span>
                </td>
                <td className="px-10 py-6 border-l border-slate-100">
                  <div className="space-y-1">
                    <p className="text-slate-900 text-xs font-black">{hostel.totalRooms} Rooms</p>
                    <p className="text-slate-400 text-[9px] uppercase font-bold tracking-widest">{hostel.totalBlocks} Blocks</p>
                  </div>
                </td>
                <td className="px-10 py-6 border-l border-slate-100">
                  <div className="flex flex-col gap-1">
                    <span className="text-primary-800 text-[11px] font-black uppercase tracking-tight">{hostel.wardenName}</span>
                    <span className="text-[10px] text-slate-400 font-mono font-bold">{hostel.contactNumber}</span>
                  </div>
                </td>
                <td className="px-10 py-6 border-l border-slate-100">
                  <div className={`px-4 py-1.5 rounded-xl text-[9px] font-black uppercase tracking-widest inline-flex items-center gap-2 border ${
                    hostel.status === 'ACTIVE' ? 'bg-emerald-50 text-emerald-600 border-emerald-100' : 'bg-rose-50 text-rose-600 border-rose-100'
                  }`}>
                    <div className={`h-1 w-1 rounded-full ${hostel.status === 'ACTIVE' ? 'bg-emerald-500' : 'bg-rose-500'}`}></div>
                    {hostel.status}
                  </div>
                </td>
              </tr>
            ))}

            {activeTab === 'rooms' && rooms.map((room, idx) => (
              <tr key={idx} className="hover:bg-slate-50 transition-all group">
                <td className="px-10 py-6 font-mono text-[10px] text-slate-400 font-bold">#{room.roomId}</td>
                <td className="px-10 py-6 border-l border-slate-100">
                   <div className="flex items-center gap-3">
                      <div className="h-8 w-8 rounded-lg bg-slate-100 flex items-center justify-center text-slate-600 group-hover:bg-primary-600 group-hover:text-white transition-all">
                         <DoorOpen size={14} />
                      </div>
                      <span className="text-slate-900 text-[13px] font-black font-mono">{room.roomNumber}</span>
                   </div>
                </td>
                <td className="px-10 py-6 border-l border-slate-100 text-slate-500 text-[11px] font-black uppercase tracking-tight">{room.hostelName}</td>
                <td className="px-10 py-6 border-l border-slate-100 font-black text-[10px] text-slate-400 uppercase tracking-widest">{room.sharingType}</td>
                <td className="px-10 py-6 border-l border-slate-100">
                   <div className="flex items-center gap-3">
                      <div className="h-1.5 w-24 bg-slate-100 rounded-full overflow-hidden">
                         <div className="h-full bg-primary-600" style={{ width: `${(room.currentlyOccupied/getSharingCapacity(room.sharingType))*100}%` }}></div>
                      </div>
                      <span className="text-[11px] font-black text-slate-900">{room.currentlyOccupied} / {getSharingCapacity(room.sharingType)}</span>
                   </div>
                </td>
                <td className="px-10 py-6 border-l border-slate-100">
                  <div className={`px-4 py-1.5 rounded-xl text-[9px] font-black uppercase tracking-widest border ${
                    room.status === 'AVAILABLE' ? 'bg-emerald-50 text-emerald-600 border-emerald-100' : 
                    room.status === 'FULL' ? 'bg-rose-50 text-rose-600 border-rose-100' : 
                    'bg-amber-50 text-amber-600 border-amber-100'
                  }`}>
                    {room.status}
                  </div>
                </td>
              </tr>
            ))}

            {activeTab === 'students' && students.map((student, idx) => (
              <tr key={idx} className="hover:bg-slate-50 transition-all group">
                <td className="px-10 py-6 font-mono text-[10px] text-slate-400 font-bold">#{student.allocationId}</td>
                <td className="px-10 py-6 border-l border-slate-100">
                  <div className="flex items-center gap-4">
                    <div className="h-10 w-10 rounded-2xl bg-primary-600/10 flex items-center justify-center text-primary-600 border border-primary-600/20">
                       <UserCheck size={20} />
                    </div>
                    <div className="space-y-0.5">
                       <p className="text-slate-900 text-[13px] font-black uppercase tracking-tight">{student.studentName}</p>
                       <p className="text-slate-400 text-[10px] lowercase font-bold">{student.studentEmail}</p>
                    </div>
                  </div>
                </td>
                <td className="px-10 py-6 border-l border-slate-100">
                   <div className="flex flex-col gap-1">
                      <span className="text-slate-700 text-[11px] font-black uppercase tracking-tight">{student.parentName}</span>
                      <div className="flex items-center gap-2">
                         <Phone size={10} className="text-slate-400" />
                         <span className="text-slate-400 text-[10px] font-mono font-bold">{student.parentPhone}</span>
                      </div>
                   </div>
                </td>
                <td className="px-10 py-6 border-l border-slate-100">
                   <div className="flex flex-col gap-1">
                      <span className="text-slate-400 text-[10px] font-black uppercase tracking-tight">{student.hostelName}</span>
                      <span className="text-primary-800 text-[11px] font-black font-mono">#{student.roomNumber}</span>
                   </div>
                </td>
                <td className="px-10 py-6 border-l border-slate-100 font-mono text-[10px] text-slate-400 font-bold">{student.joinDate}</td>
                <td className="px-10 py-6 border-l border-slate-100">
                  <div className={`px-4 py-1.5 rounded-xl text-[9px] font-black uppercase tracking-widest inline-flex items-center gap-2 border ${
                    student.status === 'ACTIVE' ? 'bg-emerald-50 text-emerald-600 border-emerald-100' : 
                    student.status === 'CHECKED_OUT' ? 'bg-slate-50 text-slate-600 border-slate-100' : 
                    'bg-rose-50 text-rose-600 border-rose-100'
                  }`}>
                    {student.status.replace('_', ' ')}
                  </div>
                </td>
              </tr>
            ))}

            {activeTab === 'attendance' && attendance.map((entry, idx) => (
              <tr key={idx} className="hover:bg-slate-50 transition-all group">
                <td className="px-10 py-6 font-mono text-[10px] text-slate-400 font-bold">#{entry.attendanceId}</td>
                <td className="px-10 py-6 border-l border-slate-100">
                  <span className="text-slate-900 text-[13px] font-black uppercase tracking-tight">{entry.studentName}</span>
                </td>
                <td className="px-10 py-6 border-l border-slate-100 font-mono text-[11px] text-primary-800">Room-X-{entry.roomNumber}</td>
                <td className="px-10 py-6 border-l border-slate-100 text-slate-400 text-[10px] font-bold">{entry.attendanceDate}</td>
                <td className="px-10 py-6 border-l border-slate-100">
                  <div className={`px-4 py-1.5 rounded-xl text-[9px] font-black uppercase tracking-widest border ${
                    entry.status === 'PRESENT' ? 'bg-emerald-50 text-emerald-600 border-emerald-100' : 'bg-rose-50 text-rose-600 border-rose-100'
                  }`}>
                    {entry.status}
                  </div>
                </td>
              </tr>
            ))}

            {activeTab === 'complaints' && complaints.map((complaint, idx) => (
              <tr key={idx} className="hover:bg-slate-50 transition-all group">
                <td className="px-10 py-6 font-mono text-[10px] text-slate-400 font-bold">#{complaint.complaintId}</td>
                <td className="px-10 py-6 border-l border-slate-100">
                  <div className="flex flex-col gap-1">
                    <span className="text-slate-900 text-[11px] font-black uppercase tracking-tight">{complaint.issueCategory}</span>
                    <span className="text-[9px] text-slate-400 truncate max-w-[200px] font-medium">{complaint.description}</span>
                  </div>
                </td>
                <td className="px-10 py-6 border-l border-slate-100 text-slate-500 text-[11px] font-black uppercase tracking-tight">{complaint.studentName}</td>
                <td className="px-10 py-6 border-l border-slate-100">
                  <span className={`text-[9px] font-black px-2 py-1 rounded-md ${
                    complaint.priority === 'HIGH' ? 'text-rose-600 bg-rose-50' : 
                    complaint.priority === 'MEDIUM' ? 'text-amber-600 bg-amber-50' : 
                    'text-primary-600 bg-primary-50'
                  }`}>
                    {complaint.priority}
                  </span>
                </td>
                <td className="px-10 py-6 border-l border-slate-100 text-[10px] font-black text-slate-400 uppercase tracking-widest">{complaint.status.replace('_', ' ')}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className="p-8 bg-slate-50/50 flex items-center justify-between border-t border-slate-100">
         <span className="text-[10px] font-black text-slate-300 uppercase tracking-[0.5em]">Backend Synchronized Registry v5.0S</span>
         <div className="flex items-center gap-6">
            <button className="text-[10px] font-black text-slate-400 hover:text-slate-900 transition-all uppercase tracking-widest">Previous Batch</button>
            <div className="h-4 w-[1px] bg-slate-200"></div>
            <button className="text-[10px] font-black text-primary-600 hover:text-primary-800 transition-all uppercase tracking-widest underline decoration-2 underline-offset-4">Next Registry Node</button>
         </div>
      </div>
    </motion.div>
  )
}

export default HostelDataGrid
