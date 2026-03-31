import { Bus, MapPin, MoreVertical, Plus, Zap, Navigation, Cpu, ArrowUpRight } from 'lucide-react'
import { motion } from 'framer-motion'

const TransportManagement = () => {
  const routes = [
    { id: 'R01', name: 'Downtown_Express', stops: 12, vehicle: 'Bus_102', status: 'ACTIVE', color: 'from-primary-500 to-sky-400', load: '88%' },
    { id: 'R04', name: 'North_Campus_Sync', stops: 6, vehicle: 'Bus_304', status: 'DELAYED', color: 'from-rose-500 to-amber-400', load: '42%' },
    { id: 'R07', name: 'West_Gate_Orbital', stops: 8, vehicle: 'Bus_082', status: 'ACTIVE', color: 'from-emerald-500 to-teal-400', load: '95%' },
    { id: 'R12', name: 'Terminal_Shuttle', stops: 4, vehicle: 'Bus_112', status: 'STABLE', color: 'from-slate-500 to-slate-400', load: '12%' },
  ]

  const containerVariants = {
    hidden: { opacity: 0 },
    visible: { opacity: 1, transition: { staggerChildren: 0.1 } }
  }

  const itemVariants = {
    hidden: { y: 20, opacity: 0 },
    visible: { y: 0, opacity: 1 }
  }

  return (
    <motion.div variants={containerVariants} initial="hidden" animate="visible" className="space-y-12 pb-24">
      {/* Logistics Header */}
      <div className="flex flex-col xl:flex-row xl:items-end justify-between gap-10 border-b border-white/[0.03] pb-12">
        <div className="max-w-2xl">
          <motion.div 
            initial={{ scale: 0.9, opacity: 0 }}
            animate={{ scale: 1, opacity: 1 }}
            className="flex items-center gap-4 mb-6"
          >
            <div className="h-12 w-12 rounded-2xl bg-primary-500/10 flex items-center justify-center text-primary-400 border border-primary-500/20 shadow-2xl">
               <Bus size={24} />
            </div>
            <h1 className="text-4xl font-black text-white tracking-tighter uppercase tracking-[-0.04em]">Transport Management</h1>
          </motion.div>
          <p className="text-slate-500 text-lg font-medium leading-relaxed">
            Manage fleet routes, vehicle tracking and transportation schedules.
          </p>
        </div>
        <button className="flex items-center gap-4 bg-white text-slate-950 px-10 py-5 rounded-2xl font-black text-xs uppercase tracking-[0.2em] hover:bg-primary-50 active:scale-95 transition-all shadow-2xl">
          <Plus size={20} strokeWidth={3} />
          Add Route
        </button>
      </div>

      {/* Fleet Overview Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
        {routes.map((route, idx) => (
          <motion.div key={idx} variants={itemVariants} className="glass-v2 group overflow-hidden border border-white/[0.03] rounded-[2.5rem] relative">
            <div className={`h-2 w-full bg-gradient-to-r ${route.color}`} />
            
            <div className="p-10 relative">
              <div className="flex justify-between items-start mb-10">
                <div className="min-w-0">
                  <h3 className="text-2xl font-black text-white group-hover:text-wow transition-all duration-500 uppercase tracking-tighter truncate">{route.name}</h3>
                  <div className="flex items-center space-x-2 mt-1">
                     <span className="text-[9px] text-slate-700 font-black uppercase tracking-[0.3em]">CID_{route.id}</span>
                     <div className="h-1 w-1 bg-slate-800 rounded-full" />
                     <span className="text-[9px] text-slate-700 font-bold uppercase tracking-widest">{route.vehicle}</span>
                  </div>
                </div>
                <button className="text-slate-800 hover:text-white transition-colors">
                  <MoreVertical size={20} />
                </button>
              </div>

              <div className="space-y-6 mb-10">
                <div className="flex items-center justify-between p-4 rounded-2xl bg-slate-950/40 border border-white/[0.02] group-hover:border-white/[0.05] transition-all">
                  <div className="flex items-center gap-4">
                     <MapPin size={18} className="text-slate-600" />
                     <span className="text-[11px] text-slate-400 font-black uppercase tracking-widest">Active Stops</span>
                  </div>
                  <span className="text-sm font-black text-white">{route.stops}</span>
                </div>
                
                <div className="flex items-center justify-between p-4 rounded-2xl bg-slate-950/40 border border-white/[0.02] group-hover:border-white/[0.05] transition-all">
                  <div className="flex items-center gap-4">
                     <Cpu size={18} className="text-slate-600" />
                     <span className="text-[11px] text-slate-400 font-black uppercase tracking-widest">Unit Load</span>
                  </div>
                  <span className={`text-sm font-black ${parseInt(route.load) > 90 ? 'text-rose-400' : 'text-emerald-400'}`}>{route.load}</span>
                </div>
              </div>

              <div className="pt-8 border-t border-white/[0.03] flex items-center justify-between">
                <div className={`px-4 py-2 rounded-xl text-[9px] font-black uppercase tracking-[0.2em] flex items-center gap-3 ${
                  route.status === 'ACTIVE' ? 'bg-emerald-500/10 text-emerald-400 border-emerald-500/20' : 
                  route.status === 'DELAYED' ? 'bg-rose-500/10 text-rose-400 border-rose-500/20' : 
                  'bg-slate-500/10 text-slate-600 border-slate-500/20'
                }`}>
                  <div className={`h-1.5 w-1.5 rounded-full animate-pulse shadow-[0_0_8px] ${
                    route.status === 'ACTIVE' ? 'bg-emerald-400 shadow-emerald-400/40' : route.status === 'DELAYED' ? 'bg-rose-400 shadow-rose-400/40' : 'bg-slate-600'
                  }`} />
                  {route.status}
                </div>
                
                <button className="text-[10px] font-black text-primary-400 hover:text-white uppercase tracking-widest transition-all flex items-center gap-2 group/btn">
                  ORBITAL SYNC
                  <ArrowUpRight size={14} className="group-hover/btn:translate-x-1 group-hover/btn:-translate-y-1 transition-transform" />
                </button>
              </div>
            </div>
            
            {/* Background Accent */}
            <div className="absolute -bottom-10 -left-10 h-32 w-32 bg-white/5 blur-3xl rounded-full opacity-0 group-hover:opacity-100 transition-opacity pointer-events-none" />
          </motion.div>
        ))}
      </div>

      {/* Satellite Management Hub */}
      <motion.div variants={itemVariants} className="glass-ultra h-[650px] w-full flex flex-col items-center justify-center relative overflow-hidden bg-slate-950/40 rounded-[3rem] border border-white/[0.02] shadow-2xl">
        <div className="absolute inset-0 opacity-[0.05] bg-[url('https://www.transparenttextures.com/patterns/grid-me-bright.png')]"></div>
        <div className="absolute inset-0 bg-gradient-to-t from-primary-500/10 via-transparent to-transparent flex items-center justify-center">
           <div className="h-[600px] w-[600px] border border-primary-500/5 rounded-full animate-[spin_60s_linear_infinite]" />
           <div className="absolute h-[450px] w-[450px] border border-primary-500/10 rounded-full animate-[spin_40s_linear_infinite_reverse]" />
           <div className="absolute h-[300px] w-[300px] border border-white/5 rounded-full" />
        </div>
        
        <div className="relative z-10 flex flex-col items-center text-center max-w-xl px-10">
           <motion.div 
             animate={{ rotate: 360 }}
             transition={{ duration: 20, repeat: Infinity, ease: "linear" }}
             className="h-28 w-28 rounded-[2rem] bg-slate-950 border border-white/[0.04] flex items-center justify-center text-primary-400 mb-12 shadow-[0_0_60px_rgba(59,130,246,0.2)]"
           >
              <Navigation size={48} strokeWidth={1} />
           </motion.div>
           
           <h3 className="text-4xl font-black text-white mb-6 uppercase tracking-tighter leading-none">Global Vector Archive</h3>
           <p className="text-slate-500 text-lg lg:text-xl font-medium mb-12 leading-relaxed">
             Establish an encrypted satellite link to visualized high-resolution fleet positioning data across Sectors-82/A and 44_C.
           </p>
           
           <div className="flex flex-wrap items-center justify-center gap-6">
              <button className="px-12 py-5 bg-primary-500 text-white rounded-[1.5rem] text-[11px] font-black uppercase tracking-[0.4em] hover:bg-primary-400 hover:shadow-[0_0_50px_rgba(59,130,246,0.4)] transition-all flex items-center gap-4 group">
                 <Zap size={20} fill="currentColor" />
                 Initiate Satellite Link
              </button>
              <button className="px-10 py-5 bg-slate-950/60 border border-white/[0.04] text-slate-500 rounded-[1.5rem] text-[11px] font-black uppercase tracking-[0.4em] hover:text-white transition-all">
                 Vector Diagnostics
              </button>
           </div>
        </div>

        {/* Global Positioning Labels */}
        <div className="absolute top-12 left-12 flex flex-col gap-4">
           <div className="flex items-center gap-3">
              <div className="h-2 w-2 rounded-full bg-emerald-500 animate-pulse" />
              <span className="text-[10px] font-black text-slate-700 uppercase tracking-[0.5em]">SYSTEM_STBL</span>
           </div>
           <div className="h-10 border-l border-white/5 ml-1" />
           <div className="text-[10px] font-black text-slate-800 uppercase tracking-[0.5em] [writing-mode:vertical-lr] opacity-40">GS_MATRIX_B8</div>
        </div>
        
        <div className="absolute bottom-12 right-12 text-right">
           <span className="text-[12px] font-mono text-slate-800 block mb-2 opacity-60">LOC: 23.9928N / 82.1102W</span>
           <span className="text-[10px] font-black text-slate-700 uppercase tracking-[0.4em]">SYNC_READY_PROTO.V4</span>
        </div>
      </motion.div>
    </motion.div>
  )
}

export default TransportManagement
