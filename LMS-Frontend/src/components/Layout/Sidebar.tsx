import { NavLink } from 'react-router-dom'
import { 
  LayoutDashboard, 
  Building2, 
  Bus, 
  Library, 
  Settings, 
  LogOut, 
  ShieldCheck,
  Hexagon
} from 'lucide-react'
import { motion } from 'framer-motion'

const Sidebar = () => {
  const navItems = [
    { name: 'Dashboard', icon: <LayoutDashboard size={20} />, path: '/' },
    { name: 'Hostel', icon: <Building2 size={20} />, path: '/hostel' },
    { name: 'Transport', icon: <Bus size={20} />, path: '/transport' },
    { name: 'Library', icon: <Library size={20} />, path: '/library' },
    { name: 'Settings', icon: <Settings size={20} />, path: '/settings' },
  ]

  return (
    <motion.aside 
      initial={{ x: -20, opacity: 0 }}
      animate={{ x: 0, opacity: 1 }}
      className="w-72 min-h-screen glass-ultra border-r-0 rounded-none flex flex-col p-8 z-50 sticky top-0 h-screen overflow-hidden"
    >
      {/* Branding - Futuristic */}
      <div className="flex items-center space-x-4 mb-16 px-1 group cursor-pointer">
        <div className="relative">
          <div className="absolute inset-0 bg-indigo-500 blur-2xl opacity-40 group-hover:opacity-100 group-hover:scale-150 transition-all duration-700"></div>
          <div className="relative h-12 w-12 bg-slate-950 border border-white/10 rounded-2xl flex items-center justify-center shadow-2xl rotate-3 group-hover:rotate-0 transition-transform duration-500">
            <Hexagon size={24} className="text-indigo-400 fill-indigo-400/10" strokeWidth={1.5} />
          </div>
        </div>
        <div>
          <span className="font-extrabold text-2xl tracking-tighter text-white block leading-none">CAMPUS</span>
          <span className="text-[9px] font-black text-indigo-400/80 tracking-[0.4em] uppercase mt-1 block">Management System</span>
        </div>
      </div>

      {/* Navigation - High Contrast */}
      <nav className="flex-1 flex flex-col space-y-2 overflow-y-auto pr-2 custom-scrollbar">
        {navItems.map((item) => (
          <NavLink
            key={item.name}
            to={item.path}
            className={({ isActive }: { isActive: boolean }) => `
              group relative flex items-center justify-between px-5 py-4 rounded-2xl transition-all duration-500
              ${isActive 
                ? 'bg-white/5 text-white shadow-inner' 
                : 'text-slate-500 hover:text-slate-200'}
            `}
          >
            <div className="flex items-center space-x-4 z-10">
              <span className={`transition-all duration-500 group-hover:scale-125 group-hover:text-indigo-400 ${
                // Subtle glow for active icon
                'opacity-80'
              }`}>
                {item.icon}
              </span>
              <span className="font-bold text-sm tracking-tight uppercase tracking-widest text-[11px]">{item.name}</span>
            </div>
            
            <div className="flex items-center">
              <NavLink
                to={item.path}
                className={({ isActive }: { isActive: boolean }) => 
                  isActive ? "opacity-100 scale-100" : "opacity-0 scale-50"
                }
              >
                <div className="h-1.5 w-1.5 rounded-full bg-indigo-400 shadow-[0_0_12px_rgba(129,140,248,0.8)]" />
              </NavLink>
            </div>

            {/* Active Highlight Bar */}
            <NavLink
               to={item.path}
               className={({ isActive }: { isActive: boolean }) => 
                `absolute inset-y-2 left-0 w-1 bg-indigo-400 rounded-full transition-all duration-500 ${isActive ? 'opacity-100 scale-100' : 'opacity-0 scale-0'}`
               }
            />
          </NavLink>
        ))}
      </nav>

      {/* System Status / User */}
      <div className="mt-auto pt-8 border-t border-white/[0.03] space-y-6">
        <div className="p-5 rounded-3xl bg-white/[0.02] border border-white/[0.03] relative group overflow-hidden cursor-pointer">
           <div className="absolute inset-0 bg-indigo-500/5 opacity-0 group-hover:opacity-100 transition-opacity" />
           
           <div className="flex items-center space-x-4 relative z-10">
              <div className="relative">
                 <div className="h-10 w-10 rounded-full bg-slate-800 border border-white/10 flex items-center justify-center overflow-hidden">
                    <ShieldCheck size={20} className="text-emerald-400" />
                 </div>
                 <div className="absolute -bottom-0.5 -right-0.5 h-3 w-3 bg-emerald-500 border-2 border-slate-950 rounded-full shadow-lg" />
              </div>
              <div className="flex-1 min-w-0">
                 <span className="text-[11px] font-black text-white block truncate uppercase tracking-tighter">Root Administrator</span>
                 <span className="text-[9px] text-slate-600 block uppercase font-bold tracking-widest mt-0.5">Secure Session</span>
              </div>
           </div>
        </div>

        <button className="flex items-center justify-center space-x-3 px-6 py-4 w-full text-slate-600 hover:text-rose-400 hover:bg-rose-500/5 rounded-2xl transition-all group border border-transparent hover:border-rose-500/10">
          <LogOut size={16} className="group-hover:-translate-x-1 transition-transform" />
          <span className="text-[10px] font-black tracking-[0.2em] uppercase">Terminate</span>
        </button>
      </div>
    </motion.aside>
  )
}

export default Sidebar
