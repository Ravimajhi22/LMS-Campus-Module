import { Bell, Search, User, Command, Globe, Zap } from 'lucide-react'
import { motion } from 'framer-motion'

const Header = () => {
  return (
    <motion.header 
      initial={{ y: -30, opacity: 0 }}
      animate={{ y: 0, opacity: 1 }}
      transition={{ duration: 0.6, ease: [0.23, 1, 0.32, 1] as any }}
      className="h-24 px-12 flex items-center justify-between sticky top-0 z-40"
    >
      {/* Search - Terminal Style */}
      <div className="flex-1 flex items-center pr-12">
        <div className="group relative flex items-center bg-slate-950/40 border border-white/[0.04] rounded-2xl px-6 py-3 w-full max-w-2xl transition-all focus-within:ring-2 focus-within:ring-indigo-500/20 focus-within:bg-slate-950/60 focus-within:border-indigo-500/30 overflow-hidden shadow-2xl">
          <div className="absolute inset-0 bg-indigo-500/5 opacity-0 group-focus-within:opacity-100 transition-opacity" />
          <Search size={18} className="text-slate-600 group-focus-within:text-indigo-400 transition-colors relative z-10" />
          <input 
            type="text" 
            placeholder="Search resources, students, or records..." 
            className="bg-transparent border-none outline-none text-[13px] w-full ml-4 text-slate-200 placeholder-slate-700 font-medium relative z-10" 
          />
          <div className="flex items-center space-x-1.5 bg-white/[0.03] px-2.5 py-1 rounded-xl border border-white/[0.06] ml-4 shrink-0 relative z-10">
            <Command size={10} className="text-slate-600" />
            <span className="text-[10px] font-black text-slate-600 uppercase">K</span>
          </div>
        </div>
      </div>

      <div className="flex items-center space-x-6">
        {/* Environment Status */}
        <div className="hidden xl:flex items-center space-x-6 px-6 py-2.5 rounded-2xl bg-white/[0.02] border border-white/[0.03] mr-2">
           <div className="flex items-center space-x-2">
              <div className="h-1.5 w-1.5 rounded-full bg-emerald-500 shadow-[0_0_8px_rgba(16,185,129,0.5)]"></div>
              <span className="text-[10px] font-black text-slate-600 uppercase tracking-widest">Global Status: Optimal</span>
           </div>
           <div className="h-3 w-[1px] bg-white/[0.04]"></div>
           <div className="flex items-center space-x-2 text-indigo-400/80 hover:text-indigo-400 transition-colors cursor-pointer">
              <Globe size={14} />
              <span className="text-[10px] font-black uppercase tracking-widest">Cluster 82_A</span>
           </div>
        </div>

        {/* Notifications & Actions */}
        <div className="flex items-center space-x-2 p-1.5 rounded-2xl bg-white/[0.02] border border-white/[0.04]">
          <button className="p-3 text-slate-600 hover:text-white hover:bg-white/[0.04] rounded-xl transition-all relative group">
            <Bell size={18} strokeWidth={2.5} />
            <span className="absolute top-3.5 right-3.5 h-1.5 w-1.5 bg-indigo-500 rounded-full ring-2 ring-slate-950 animate-pulse"></span>
            <div className="absolute inset-0 rounded-xl bg-indigo-500/10 opacity-0 group-hover:opacity-100 transition-opacity" />
          </button>
          <button className="p-3 text-slate-600 hover:text-white hover:bg-white/[0.04] rounded-xl transition-all">
            <Zap size={18} strokeWidth={2.5} />
          </button>
        </div>

        {/* User Identity - Stylized */}
        <div className="flex items-center space-x-4 group cursor-pointer pl-6 border-l border-white/[0.04]">
          <div className="text-right hidden sm:block">
            <h4 className="text-[12px] font-black text-white tracking-widest group-hover:text-indigo-400 transition-colors uppercase leading-none mb-1">Abhay Admin</h4>
            <div className="flex items-center justify-end">
               <span className="text-[9px] font-black text-indigo-400/60 uppercase tracking-widest px-2 py-0.5 rounded-md bg-indigo-500/10 [letter-spacing:0.1em]">Administrator</span>
            </div>
          </div>
          <div className="relative group-hover:scale-110 transition-transform duration-500">
             <div className="absolute inset-0 bg-indigo-500/20 blur-xl opacity-0 group-hover:opacity-100 transition-opacity"></div>
             <div className="relative h-12 w-12 rounded-2xl p-0.5 bg-gradient-to-tr from-indigo-500/40 via-sky-400/40 to-slate-800 shadow-2xl">
               <div className="h-full w-full rounded-[14px] bg-slate-900 flex items-center justify-center text-white border border-white/[0.04]">
                 <User size={22} className="text-indigo-400/80 group-hover:text-indigo-400 transition-colors" />
               </div>
             </div>
          </div>
        </div>
      </div>
    </motion.header>
  )
}

export default Header
