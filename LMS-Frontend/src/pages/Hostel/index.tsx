import { useState } from 'react'
import { Building, DoorOpen, Users, Plus, Filter, Search, Zap, UserCheck, Hash, ShieldCheck, MapPin } from 'lucide-react'
import { motion, AnimatePresence } from 'framer-motion'

const HostelManagement = () => {
  const [activeTab, setActiveTab] = useState('hostels')

  const tabs = [
    { id: 'hostels', name: 'Hostels', icon: <Building size={18} /> },
    { id: 'rooms', name: 'Rooms', icon: <DoorOpen size={18} /> },
    { id: 'students', name: 'Students', icon: <Users size={18} /> },
  ]

  // Mock Data aligned with Backend Entities
  const hostels = [
    { name: 'Evergreen Hall', type: 'MEN', blocks: 4, rooms: 120, warden: 'Dr. Rahul Sharma', contact: '+91 98765 43210', status: 'ACTIVE' },
    { name: 'Bluebird Residency', type: 'WOMEN', blocks: 3, rooms: 95, warden: 'Ms. Anita Patel', contact: '+91 98765 43211', status: 'ACTIVE' },
    { name: 'Oak Tower', type: 'MEN', blocks: 5, rooms: 150, warden: 'Prof. Mark Davis', contact: '+91 98765 43212', status: 'ACTIVE' },
    { name: 'Skyview Suites', type: 'COLIVING', blocks: 2, rooms: 45, warden: 'Mr. David Lee', contact: '+91 98765 43213', status: 'INACTIVE' },
  ]

  const rooms = [
    { number: '101A', type: 'SINGLE', occupied: 1, capacity: 1, status: 'FULL', hostel: 'Evergreen Hall' },
    { number: '204B', type: 'DOUBLE', occupied: 1, capacity: 2, status: 'PARTIALLY_FILLED', hostel: 'Bluebird Residency' },
    { number: '302C', type: 'TRIPLE', occupied: 0, capacity: 3, status: 'AVAILABLE', hostel: 'Oak Tower' },
    { number: 'G-12', type: 'QUAD', occupied: 4, capacity: 4, status: 'FULL', hostel: 'Evergreen Hall' },
  ]

  const students = [
    { name: 'Abhay Kumar', email: 'abhay.k@univ.edu', hostel: 'Evergreen Hall', room: '101A', joinDate: '2026-01-15', status: 'ACTIVE' },
    { name: 'Priya Singh', email: 'priya.s@univ.edu', hostel: 'Bluebird Residency', room: '204B', joinDate: '2026-02-01', status: 'ACTIVE' },
    { name: 'John Doe', email: 'john.d@univ.edu', hostel: 'Oak Tower', room: '302C', joinDate: '2025-11-20', status: 'CHECKED_OUT' },
    { name: 'Rohan Mehta', email: 'rohan.m@univ.edu', hostel: 'Evergreen Hall', room: 'G-12', joinDate: '2026-03-01', status: 'CANCELLED' },
  ]

  const tableVariants = {
    hidden: { y: 15, opacity: 0 },
    visible: { y: 0, opacity: 1, transition: { duration: 0.4, ease: "easeOut" as any } }
  }

  return (
    <div className="space-y-12 pb-24">
      {/* Header Section */}
      <div className="flex flex-col xl:flex-row xl:items-end justify-between gap-10 border-b border-white/[0.03] pb-12">
        <div className="max-w-2xl">
          <motion.div 
            initial={{ x: -20, opacity: 0 }}
            animate={{ x: 0, opacity: 1 }}
            className="flex items-center gap-3 mb-6"
          >
            <div className="h-10 w-10 rounded-2xl bg-indigo-500/10 flex items-center justify-center text-indigo-400 border border-indigo-500/20 shadow-2xl">
               <Building size={20} />
            </div>
            <h1 className="text-4xl font-black text-white tracking-tighter uppercase">Hostel Management</h1>
          </motion.div>
          <p className="text-slate-500 text-lg font-medium leading-relaxed">
            Configure residential entities, allocate modules and manage student occupancy rosters aligned with core data systems.
          </p>
          
          <div className="flex items-center gap-2 bg-slate-950/40 p-2 rounded-2xl w-max mt-10 border border-white/[0.04]">
            {tabs.map((tab) => (
              <button
                key={tab.id}
                onClick={() => setActiveTab(tab.id)}
                className={`flex items-center gap-3 px-6 py-3 rounded-[14px] text-[10px] font-black uppercase tracking-widest transition-all duration-500 ${
                  activeTab === tab.id 
                  ? 'bg-indigo-500 text-white shadow-2xl shadow-indigo-500/40' 
                  : 'text-slate-600 hover:text-slate-200'
                }`}
              >
                {tab.icon}
                {tab.name}
              </button>
            ))}
          </div>
        </div>

        <div className="flex items-center gap-5">
          <button className="flex items-center gap-4 bg-white text-slate-950 px-10 py-5 rounded-2xl font-black text-xs uppercase tracking-[0.2em] hover:bg-slate-50 transition-all shadow-2xl">
            <Plus size={20} strokeWidth={3} />
            Add {activeTab.slice(0, -1)}
          </button>
        </div>
      </div>

      {/* Global Filter Bar */}
      <div className="flex flex-wrap items-center gap-6">
        <div className="flex-1 min-w-[400px] group relative flex items-center bg-slate-950/40 border border-white/[0.04] rounded-2xl px-6 py-4 text-slate-400 focus-within:ring-2 focus-within:ring-indigo-500/20 transition-all">
          <Search size={20} className="group-focus-within:text-indigo-400 transition-colors" />
          <input 
            type="text" 
            placeholder={`Filter ${activeTab} records by primary identifiers...`} 
            className="bg-transparent border-none outline-none text-sm w-full ml-4 text-slate-300 placeholder-slate-700 font-bold uppercase tracking-tight" 
          />
        </div>
        <button className="flex items-center gap-4 px-8 py-4 rounded-2xl bg-slate-950/40 border border-white/[0.04] text-slate-500 hover:text-white transition-all shadow-xl">
          <Filter size={18} />
          <span className="text-[10px] font-black uppercase tracking-widest">Filter Matrix</span>
        </button>
      </div>

      {/* Data Matrix - Optimized Table View */}
      <AnimatePresence mode="wait">
        <motion.div
          key={activeTab}
          variants={tableVariants}
          initial="hidden"
          animate="visible"
          exit={{ opacity: 0, y: -10 }}
          className="glass-ultra overflow-hidden border border-white/[0.02] rounded-[2.5rem] shadow-2xl"
        >
          <div className="overflow-x-auto">
            <table className="w-full text-left">
              <thead className="bg-slate-950/40 divide-y divide-white/[0.03]">
                <tr>
                  {activeTab === 'hostels' && (
                    <>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Hostel Name</th>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Entity Type</th>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Capacities</th>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Primary Warden</th>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Contact Identifier</th>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Unit Status</th>
                    </>
                  )}
                  {activeTab === 'rooms' && (
                    <>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Room Number</th>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Parent Hostel</th>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Sharing Mode</th>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Occupancy Sync</th>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Node Status</th>
                    </>
                  )}
                  {activeTab === 'students' && (
                    <>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Student Profile</th>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Assigned Hostel</th>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Room Sector</th>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Join Timestamp</th>
                      <th className="px-10 py-8 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.03]">Registry Status</th>
                    </>
                  )}
                </tr>
              </thead>
              <tbody className="divide-y divide-white/[0.02]">
                {activeTab === 'hostels' && hostels.map((hostel, idx) => (
                  <tr key={idx} className="hover:bg-white/[0.01] transition-all group">
                    <td className="px-10 py-6">
                      <div className="flex items-center gap-5">
                        <div className="h-10 w-10 rounded-xl bg-indigo-500/5 flex items-center justify-center text-indigo-400 border border-white/[0.03] shadow-inner">
                           <Building size={20} />
                        </div>
                        <span className="text-white text-[13px] font-black uppercase tracking-tight">{hostel.name}</span>
                      </div>
                    </td>
                    <td className="px-10 py-6">
                      <div className="flex items-center gap-3">
                         <span className="text-[10px] font-black text-white/40">{hostel.type}</span>
                      </div>
                    </td>
                    <td className="px-10 py-6">
                      <div className="space-y-1">
                        <p className="text-white text-xs font-black">{hostel.rooms} Rooms</p>
                        <p className="text-slate-600 text-[9px] uppercase font-bold tracking-widest">{hostel.blocks} Modules</p>
                      </div>
                    </td>
                    <td className="px-10 py-6">
                      <div className="flex items-center gap-4">
                        <div className="h-8 w-8 rounded-[10px] bg-slate-900 border border-white/10 flex items-center justify-center text-[10px] font-black text-indigo-400">
                          {hostel.warden.split(' ').map(n=>n[0]).join('')}
                        </div>
                        <span className="text-slate-400 text-xs font-bold uppercase">{hostel.warden}</span>
                      </div>
                    </td>
                    <td className="px-10 py-6 font-mono text-[11px] text-slate-600">{hostel.contact}</td>
                    <td className="px-10 py-6">
                      <div className={`px-4 py-1.5 rounded-xl text-[9px] font-black uppercase tracking-widest inline-flex items-center gap-2 border ${
                        hostel.status === 'ACTIVE' ? 'bg-emerald-500/10 text-emerald-400 border-emerald-500/20' : 'bg-rose-500/10 text-rose-500 border-rose-500/20'
                      }`}>
                        <div className={`h-1 w-1 rounded-full ${hostel.status === 'ACTIVE' ? 'bg-emerald-400' : 'bg-rose-400 animate-pulse'}`}></div>
                        {hostel.status}
                      </div>
                    </td>
                  </tr>
                ))}

                {activeTab === 'rooms' && rooms.map((room, idx) => (
                  <tr key={idx} className="hover:bg-white/[0.01] transition-all group">
                    <td className="px-10 py-6">
                       <div className="flex items-center gap-4">
                          <div className="h-9 w-9 rounded-xl bg-slate-950 border border-white/[0.04] flex items-center justify-center text-slate-500 group-hover:text-amber-400 transition-colors">
                             <DoorOpen size={18} />
                          </div>
                          <span className="text-white text-[13px] font-black font-mono">{room.number}</span>
                       </div>
                    </td>
                    <td className="px-10 py-6 text-slate-400 text-xs font-bold uppercase tracking-tight">{room.hostel}</td>
                    <td className="px-10 py-6 font-black text-[10px] text-slate-600 uppercase tracking-widest">{room.type}</td>
                    <td className="px-10 py-6">
                       <div className="flex items-center gap-3">
                          <div className="h-1.5 w-24 bg-white/[0.03] rounded-full overflow-hidden p-[2px]">
                             <div className="h-full bg-indigo-500 rounded-full" style={{ width: `${(room.occupied/room.capacity)*100}%` }}></div>
                          </div>
                          <span className="text-[11px] font-black text-white">{room.occupied} / {room.capacity}</span>
                       </div>
                    </td>
                    <td className="px-10 py-6">
                      <div className={`px-4 py-1.5 rounded-xl text-[9px] font-black uppercase tracking-widest inline-flex items-center gap-2 border ${
                        room.status === 'AVAILABLE' ? 'bg-emerald-500/10 text-emerald-400 border-emerald-500/20' : 
                        room.status === 'FULL' ? 'bg-rose-500/10 text-rose-500 border-rose-500/20' : 
                        'bg-amber-500/10 text-amber-400 border-amber-500/20'
                      }`}>
                        {room.status}
                      </div>
                    </td>
                  </tr>
                ))}

                {activeTab === 'students' && students.map((student, idx) => (
                  <tr key={idx} className="hover:bg-white/[0.01] transition-all group">
                    <td className="px-10 py-6">
                      <div className="flex items-center gap-4">
                        <div className="h-10 w-10 rounded-2xl bg-indigo-500/10 flex items-center justify-center text-indigo-400 border border-indigo-500/20">
                           <UserCheck size={20} />
                        </div>
                        <div className="space-y-0.5">
                           <p className="text-white text-[13px] font-black uppercase tracking-tight">{student.name}</p>
                           <p className="text-slate-600 text-[10px] lowercase font-bold">{student.email}</p>
                        </div>
                      </div>
                    </td>
                    <td className="px-10 py-6">
                       <div className="flex items-center gap-3">
                          <MapPin size={12} className="text-indigo-500/40" />
                          <span className="text-slate-400 text-xs font-bold uppercase tracking-tight">{student.hostel}</span>
                       </div>
                    </td>
                    <td className="px-10 py-6 font-black font-mono text-[11px] text-indigo-400">#{student.room}</td>
                    <td className="px-10 py-6 font-mono text-[11px] text-slate-700">{student.joinDate}</td>
                    <td className="px-10 py-6">
                      <div className={`px-4 py-1.5 rounded-xl text-[9px] font-black uppercase tracking-widest inline-flex items-center gap-2 border ${
                        student.status === 'ACTIVE' ? 'bg-emerald-500/10 text-emerald-400 border-emerald-500/20' : 
                        student.status === 'CHECKED_OUT' ? 'bg-slate-500/10 text-slate-600 border-slate-500/20' : 
                        'bg-rose-500/10 text-rose-500 border-rose-500/20'
                      }`}>
                        {student.status}
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

          <div className="p-8 bg-slate-950/40 flex items-center justify-between border-t border-white/[0.02]">
             <span className="text-[10px] font-black text-slate-700 uppercase tracking-[0.5em]">System Archive Matrix v4.2S</span>
             <div className="flex items-center gap-6">
                <button className="text-[10px] font-black text-slate-700 hover:text-white transition-all uppercase tracking-widest">Previous Page</button>
                <div className="h-4 w-[1px] bg-white/[0.04]"></div>
                <button className="text-[10px] font-black text-indigo-400 hover:text-white transition-all uppercase tracking-widest">Next Registry Node</button>
             </div>
          </div>
        </motion.div>
      </AnimatePresence>

      {/* Decorative Atmosphere Indicators */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-10">
         {[
           { label: 'Cluster Connectivity', value: 'OPTIMAL', icon: <Hash size={16} />, color: 'text-emerald-400' },
           { label: 'Node Integrity', value: 'VERIFIED', icon: <ShieldCheck size={16} />, color: 'text-indigo-400' },
           { label: 'Latency Node', value: '14MS', icon: <Zap size={16} />, color: 'text-sky-400' },
         ].map((stat, i) => (
           <div key={i} className="glass-v2 p-6 rounded-[2rem] flex items-center justify-between border border-white/[0.02] group">
              <div className="flex items-center gap-4">
                 <div className={`h-10 w-10 rounded-xl bg-slate-950 flex items-center justify-center ${stat.color} border border-white/[0.04] group-hover:scale-110 transition-transform`}>
                    {stat.icon}
                 </div>
                 <span className="text-[10px] font-black text-slate-600 uppercase tracking-widest">{stat.label}</span>
              </div>
              <span className={`text-[11px] font-black uppercase ${stat.color} tracking-tighter`}>{stat.value}</span>
           </div>
         ))}
      </div>
    </div>
  )
}

export default HostelManagement
