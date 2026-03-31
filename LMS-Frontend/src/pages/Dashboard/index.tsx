import { 
  Users, Building, Bus, BookOpen, TrendingUp, Sparkles, 
  Cpu, Zap, LayoutGrid, List, Activity, Globe, HardDrive, Shield, 
  ChevronRight, ArrowRight, BarChart3
} from 'lucide-react'
import { motion } from 'framer-motion'

// Micro-Sparkline Chart Component
const Sparkline = ({ color }: { color: string }) => (
  <svg className="w-16 h-8 opacity-40 group-hover:opacity-100 transition-opacity" viewBox="0 0 60 20">
    <path 
      d="M0 15 Q 10 5, 20 12 T 40 8 T 60 14" 
      fill="none" 
      stroke={color} 
      strokeWidth="2" 
      strokeLinecap="round" 
    />
  </svg>
)

const Dashboard = () => {
  const stats = [
    { label: 'Network Students', value: '1,284', icon: <Users size={22} />, color: '#6366f1', trend: '+12%', subtext: 'System Peak Load' },
    { label: 'Unit Occupancy', value: '86%', icon: <Building size={22} />, color: '#10b981', trend: '+5%', subtext: 'Sector 4 Active' },
    { label: 'Active Logistics', value: '24', icon: <Bus size={22} />, color: '#0ea5e9', trend: 'STABLE', subtext: 'Route Optimised' },
    { label: 'Data Archives', value: '412', icon: <BookOpen size={22} />, color: '#8b5cf6', trend: '+18%', subtext: 'Node Sync 100%' },
  ]

  const containerVariants = {
    hidden: { opacity: 0 },
    visible: { opacity: 1, transition: { staggerChildren: 0.1 } }
  }

  const cardVariants = {
    hidden: { y: 30, opacity: 0, scale: 0.98 },
    visible: { 
      y: 0, 
      opacity: 1, 
      scale: 1,
      transition: { duration: 0.5, ease: [0.23, 1, 0.32, 1] as any } 
    }
  }

  return (
    <motion.div variants={containerVariants} initial="hidden" animate="visible" className="space-y-12 pb-24">
      {/* High-Impact Hero Section */}
      <div className="relative group overflow-hidden glass-ultra rounded-[2.5rem] p-10 lg:p-14 border border-white/[0.03]">
        <div className="absolute inset-0 bg-gradient-to-br from-indigo-500/10 via-sky-500/5 to-transparent z-0"></div>
        <div className="absolute -right-24 -top-24 h-96 w-96 bg-indigo-500/10 blur-[120px] rounded-full group-hover:bg-indigo-500/20 transition-all duration-1000"></div>
        
        <div className="relative z-10 flex flex-col lg:flex-row items-center justify-between gap-14">
          <div className="max-w-3xl">
            <motion.div 
              initial={{ scale: 0.8, opacity: 0 }}
              animate={{ scale: 1, opacity: 1 }}
              className="inline-flex items-center space-x-3 px-4 py-2 bg-indigo-500/10 border border-indigo-500/20 rounded-2xl mb-8"
            >
               <Sparkles size={14} className="text-indigo-400" />
               <span className="text-[10px] font-black tracking-[0.2em] uppercase text-indigo-400/90">System Overview</span>
            </motion.div>
            
            <h1 className="text-5xl lg:text-7xl font-extrabold text-white tracking-tighter mb-6 leading-[0.9]">
              Welcome back, <br />
              <span className="text-wow">Abhay</span>
            </h1>
            
            <p className="text-slate-500 text-lg lg:text-xl max-w-xl mb-12 font-medium leading-relaxed">
              Here is what's happening with your campus today. <br className="hidden lg:block"/>
              All modules are currently running smoothly.
            </p>
            
            <div className="flex flex-wrap gap-5">
              <button className="group flex items-center space-x-3 bg-white text-slate-950 px-8 py-4 rounded-2xl font-black text-xs uppercase tracking-widest shadow-2xl hover:bg-indigo-50 shadow-white/5 transition-all active:scale-95">
                <Zap size={18} fill="currentColor" />
                <span>Primary Console</span>
                <ArrowRight size={18} className="group-hover:translate-x-2 transition-transform" />
              </button>
              <button className="flex items-center space-x-3 bg-slate-950/40 border border-white/5 text-white px-8 py-4 rounded-2xl font-black text-xs uppercase tracking-widest hover:bg-white/[0.04] hover:border-white/10 transition-all">
                <HardDrive size={18} className="text-indigo-400" />
                <span>Diagnostics</span>
              </button>
            </div>
          </div>
          
          <div className="hidden xl:flex flex-col gap-5">
             <div className="grid grid-cols-2 gap-5">
               {[
                 { label: 'CPU Load', value: '14%', icon: <Cpu />, color: 'text-indigo-400' },
                 { label: 'Threats', value: '0', icon: <Shield />, color: 'text-emerald-400' },
                 { label: 'Uptime', value: '47d', icon: <Activity />, color: 'text-sky-400' },
                 { label: 'Nodes', value: '812', icon: <Globe />, color: 'text-purple-400' },
               ].map((stat, i) => (
                 <div key={i} className="glass-v2 p-6 rounded-3xl w-40 text-center flex flex-col items-center">
                    <div className={`${stat.color} mb-3 opacity-60`}>{stat.icon}</div>
                    <p className={`text-2xl font-black tracking-tighter ${stat.color}`}>{stat.value}</p>
                    <p className="text-[9px] font-black uppercase tracking-widest text-slate-600 mt-1">{stat.label}</p>
                 </div>
               ))}
             </div>
          </div>
        </div>
      </div>

      {/* Modern Metrics Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-8">
        {stats.map((stat, idx) => (
          <motion.div variants={cardVariants} key={idx} className="glass-v2 group overflow-hidden relative border border-white/[0.03]">
            <div className="absolute top-0 right-0 p-8">
               <Sparkline color={stat.color} />
            </div>
            
            <div className="p-8">
              <div className="flex justify-between items-start mb-10">
                <div className="h-14 w-14 rounded-2xl bg-slate-950 flex items-center justify-center border border-white/[0.03] group-hover:scale-110 group-hover:rotate-6 transition-all duration-500 shadow-2xl">
                  <div style={{ color: stat.color }} className="opacity-80">
                    {stat.icon}
                  </div>
                </div>
                <div className="flex flex-col items-end">
                   <div className="text-emerald-400 text-[10px] font-black px-2.5 py-1 bg-emerald-500/10 rounded-xl border border-emerald-500/20 shadow-lg">
                     {stat.trend}
                   </div>
                   <span className="text-[9px] font-black text-slate-700 uppercase tracking-widest mt-1.5">vs v3.4</span>
                </div>
              </div>
              
              <div>
                <h3 className="text-slate-500 text-[10px] font-black uppercase tracking-[0.2em] mb-1">{stat.label}</h3>
                <p className="text-4xl font-black text-white group-hover:text-wow transition-all duration-500">{stat.value}</p>
                <div className="flex items-center space-x-2 mt-4 text-[9px] font-black text-slate-600 uppercase tracking-widest">
                   <div className="h-1 w-1 rounded-full bg-slate-700" />
                   <span>{stat.subtext}</span>
                </div>
              </div>
            </div>
            
            {/* Corner Accent */}
            <div className={`absolute bottom-0 right-0 w-16 h-16 opacity-[0.02] bg-current rounded-tl-full -mr-8 -mb-8 transition-all group-hover:scale-150`} style={{ color: stat.color }} />
          </motion.div>
        ))}
      </div>

      {/* Command Data Panels */}
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-10">
        {/* Resource Allocation - Ultra Refined */}
        <motion.div variants={cardVariants} className="lg:col-span-2 glass-ultra overflow-hidden border border-white/[0.02] rounded-[2.5rem]">
          <div className="p-10 pb-6 flex items-center justify-between border-b border-white/[0.02]">
            <div>
              <h2 className="text-2xl font-black flex items-center gap-4 text-white uppercase tracking-tighter">
                <BarChart3 size={24} className="text-indigo-400" />
                Recent Activities
              </h2>
              <p className="text-slate-600 text-xs font-bold mt-1 uppercase tracking-widest">[Live Update]</p>
            </div>
            <div className="flex space-x-2">
               <button className="h-10 w-10 flex items-center justify-center rounded-xl bg-slate-950 border border-white/5 text-slate-500 hover:text-white transition-all"><LayoutGrid size={18} /></button>
               <button className="h-10 w-10 flex items-center justify-center rounded-xl bg-white/5 border border-white/10 text-indigo-400 transition-all shadow-inner"><List size={18} /></button>
            </div>
          </div>
          
          <div className="p-0 overflow-x-auto">
            <table className="w-full text-left">
              <thead className="bg-slate-950/40">
                <tr>
                  <th className="px-10 py-6 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.02]">Internal_ID</th>
                  <th className="px-10 py-6 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.02]">Sector_Allocation</th>
                  <th className="px-10 py-6 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.02]">Node_Asset</th>
                  <th className="px-10 py-6 font-black uppercase tracking-[0.2em] text-[9px] text-slate-700 border-b border-white/[0.02]">Integrity_Status</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-white/[0.02]">
                {[
                  { id: 'SYS-001', sector: 'Evergreen_H', asset: '0x102B', status: 'ACTIVE' },
                  { id: 'SYS-015', sector: 'Bluebird_R', asset: '0x304A', status: 'WAITING' },
                  { id: 'SYS-082', sector: 'Oak_Tower', asset: '0xG-12', status: 'ACTIVE' },
                  { id: 'SYS-112', sector: 'Evergreen_H', asset: '0x405C', status: 'SYNCING' },
                  { id: 'SYS-219', sector: 'Central_Lib', asset: '0xS-42', status: 'ACTIVE' },
                ].map((row, idx) => (
                  <tr key={idx} className="hover:bg-white/[0.01] transition-all group cursor-default">
                    <td className="px-10 py-5 font-bold text-slate-400 text-xs font-mono">{row.id}</td>
                    <td className="px-10 py-5">
                       <span className="text-white text-xs font-black uppercase tracking-tight">{row.sector}</span>
                    </td>
                    <td className="px-10 py-5 text-slate-600 font-mono text-[11px]">{row.asset}</td>
                    <td className="px-10 py-5">
                      <div className={`px-4 py-1.5 rounded-xl text-[9px] font-black uppercase tracking-widest inline-flex items-center gap-2 border ${
                        row.status === 'ACTIVE' ? 'bg-emerald-500/10 text-emerald-400 border-emerald-500/20 shadow-lg shadow-emerald-500/5' : 
                        row.status === 'WAITING' ? 'bg-amber-500/10 text-amber-400 border-amber-500/20 shadow-lg shadow-amber-500/5' : 
                        'bg-sky-500/10 text-sky-400 border-sky-500/20 shadow-lg shadow-sky-500/5'
                      }`}>
                        <span className={`w-1 h-1 rounded-full animate-pulse ${
                          row.status === 'ACTIVE' ? 'bg-emerald-400' : 
                          row.status === 'WAITING' ? 'bg-amber-400' : 'bg-sky-400'
                        }`}></span>
                        {row.status}
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
          <div className="p-10 bg-slate-950/40 flex items-center justify-between">
             <span className="text-[10px] font-black text-slate-700 uppercase tracking-widest">Displaying 5 of 128 archived nodes</span>
             <button className="flex items-center gap-2 text-[10px] font-black text-indigo-400 hover:text-white transition-all uppercase tracking-[0.2em] group/btn">
               Access Full Repository
               <ChevronRight size={14} className="group-hover/btn:translate-x-1 transition-transform" />
             </button>
          </div>
        </motion.div>

        {/* Global Live Feed - Visual Feed */}
        <motion.div variants={cardVariants} className="glass-ultra flex flex-col h-full rounded-[2.5rem] border border-white/[0.02]">
          <div className="p-10 border-b border-white/[0.02]">
            <h2 className="text-2xl font-black flex items-center gap-4 text-white uppercase tracking-tighter">
              <Activity size={24} className="text-rose-500 animate-pulse" />
              Live Events
            </h2>
            <p className="text-slate-600 text-xs font-bold mt-1 uppercase tracking-widest">[Cluster_4_Inbound]</p>
          </div>
          <div className="p-10 flex-1 space-y-10">
            {[
              { title: 'Sector Allocation', desc: 'Secure booking Room 304, Sector B', time: 'MIN_12', type: 'info' },
              { title: 'Logistics Alert', desc: 'Route #4 optimization delay +15m', time: 'MIN_45', type: 'warning' },
              { title: 'Data Archive Sync', desc: '3 assets migrated to core archives', time: 'HR_2', type: 'success' },
              { title: 'Access Request', desc: 'Node #882 established secure tunnel', time: 'HR_5', type: 'info' },
              { title: 'Transaction Flow', desc: 'Ledger #442 validated and closed', time: 'HR_7', type: 'success' },
            ].map((alert, idx) => (
              <div key={idx} className="flex gap-6 group cursor-default">
                <div className="flex flex-col items-center">
                  <div className={`h-12 w-12 rounded-2xl flex items-center justify-center border transition-all duration-500 group-hover:scale-110 group-hover:shadow-[0_0_20px] ${
                    alert.type === 'success' ? 'bg-emerald-500/10 border-emerald-500/20 text-emerald-400 group-hover:shadow-emerald-500/30' : 
                    alert.type === 'warning' ? 'bg-amber-500/10 border-amber-500/20 text-amber-400 group-hover:shadow-amber-500/30' : 
                    'bg-indigo-500/10 border-indigo-500/20 text-indigo-400 group-hover:shadow-indigo-500/30'
                  }`}>
                    {alert.type === 'success' ? <TrendingUp size={18} /> : alert.type === 'warning' ? <Zap size={18} /> : <Cpu size={18} />}
                  </div>
                  {idx !== 4 && <div className="w-[1px] flex-1 bg-white/[0.03] my-3 border-r border-dashed border-white/[0.04]"></div>}
                </div>
                <div className="min-w-0">
                  <h4 className="text-[13px] font-black text-white group-hover:text-indigo-400 transition-colors uppercase tracking-tight">{alert.title}</h4>
                  <p className="text-xs text-slate-600 mt-1 line-clamp-2 leading-tight font-medium uppercase text-[10px]">{alert.desc}</p>
                  <div className="flex items-center space-x-2 mt-2">
                     <span className="text-[9px] text-slate-800 font-black tracking-widest bg-white/[0.02] px-2 py-0.5 rounded border border-white/[0.04]">{alert.time}</span>
                     <div className="h-1 w-1 rounded-full bg-slate-800" />
                     <span className="text-[9px] text-slate-800 font-bold uppercase">SECURE_LOG</span>
                  </div>
                </div>
              </div>
            ))}
          </div>
          <div className="p-8 bg-slate-950/40 mt-auto rounded-b-[2.5rem]">
            <button className="w-full text-center text-[10px] font-black text-slate-700 hover:text-white transition-all uppercase tracking-[0.5em] group">
              Audit System Log
              <ChevronRight size={14} className="inline-block ml-2 opacity-0 group-hover:opacity-100 transition-all -translate-x-4 group-hover:translate-x-0" />
            </button>
          </div>
        </motion.div>
      </div>
    </motion.div>
  )
}

export default Dashboard
