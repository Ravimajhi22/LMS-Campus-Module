import { Bell, Search, User, Globe, Command } from 'lucide-react'
import { motion } from 'framer-motion'

const Header = () => {
  return (
    <motion.header 
      initial={{ y: -30, opacity: 0 }}
      animate={{ y: 0, opacity: 1 }}
      transition={{ duration: 0.6, ease: [0.23, 1, 0.32, 1] as any }}
      className="h-20 px-12 flex items-center justify-between sticky top-0 z-40 bg-white/80 backdrop-blur-md border-b border-slate-100 shadow-sm"
    >
      {/* Search - Deep Blue Accents */}
      <div className="flex-1 flex items-center pr-12">
        <div className="group relative flex items-center bg-slate-50 border border-slate-200 rounded-2xl px-6 py-3 w-full max-w-2xl transition-all focus-within:ring-2 focus-within:ring-primary-600/10 focus-within:bg-white focus-within:border-primary-600 overflow-hidden shadow-sm">
          <Search size={18} className="text-slate-400 group-focus-within:text-primary-600 transition-colors relative z-10" />
          <input 
            type="text" 
            placeholder="Search resources, students, or records..." 
            className="bg-transparent border-none outline-none text-[13px] w-full ml-4 text-slate-900 placeholder-slate-400 font-bold relative z-10" 
          />
          <div className="flex items-center space-x-1.5 bg-white px-2.5 py-1 rounded-xl border border-slate-200 ml-4 shrink-0 relative z-10 shadow-sm">
            <Command size={10} className="text-slate-400" />
            <span className="text-[10px] font-black text-slate-400 uppercase">K</span>
          </div>
        </div>
      </div>

      <div className="flex items-center space-x-6">
        {/* Environment Status - High Contrast */}
        <div className="hidden xl:flex items-center space-x-6 px-6 py-2.5 rounded-2xl bg-slate-50 border border-slate-200 mr-2 shadow-sm">
           <div className="flex items-center space-x-2">
              <div className="h-1.5 w-1.5 rounded-full bg-emerald-500 shadow-[0_0_8px_rgba(16,185,129,0.3)]"></div>
              <span className="text-[10px] font-black text-slate-500 uppercase tracking-widest">Core Status: Active</span>
           </div>
           <div className="h-3 w-[1px] bg-slate-200"></div>
           <div className="flex items-center space-x-2 text-primary-600 hover:text-primary-800 transition-colors cursor-pointer">
              <Globe size={14} />
              <span className="text-[10px] font-black uppercase tracking-widest leading-none">Cluster REG_01</span>
           </div>
        </div>

        {/* Notifications & Actions */}
        <div className="flex items-center space-x-2 p-1.5 rounded-2xl bg-slate-50 border border-slate-200 shadow-sm text-slate-900">
          <button className="p-3 text-slate-400 hover:text-primary-600 hover:bg-white rounded-xl transition-all relative group shadow-none hover:shadow-sm">
            <Bell className="group-hover:scale-110 transition-transform" size={20} />
            <span className="absolute top-3.5 right-3.5 h-2 w-2 bg-rose-500 rounded-full ring-2 ring-white"></span>
          </button>

          {/* User Profile - Official Aesthetic */}
          <div className="flex items-center space-x-4 pl-4 border-l border-slate-200">
            <div className="hidden lg:flex flex-col items-end">
              <span className="text-sm font-black text-slate-900 leading-none">Ravi Majhi</span>
              <div className="flex items-center space-x-2 mt-1">
               <span className="text-[9px] font-black text-primary-600 uppercase tracking-widest px-2 py-0.5 rounded-md bg-primary-50 border border-primary-100">Super Administrator</span>
              </div>
            </div>
            
            <button className="relative group">
             <div className="absolute inset-0 bg-primary-600/10 blur-xl opacity-0 group-hover:opacity-100 transition-opacity"></div>
             <div className="relative h-11 w-11 rounded-2xl p-0.5 bg-gradient-to-tr from-primary-600 to-indigo-500 shadow-lg">
               <div className="h-full w-full rounded-[14px] bg-white flex items-center justify-center text-primary-600 transition-all group-hover:bg-slate-50">
                 <User size={20} strokeWidth={2.5} />
               </div>
             </div>
            </button>
          </div>
        </div>
      </div>
    </motion.header>
  )
}

export default Header
