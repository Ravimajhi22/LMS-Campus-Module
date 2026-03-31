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
      className="w-72 min-h-screen bg-white border-r border-slate-100 flex flex-col p-8 z-50 sticky top-0 h-screen overflow-hidden shadow-sm"
    >
      {/* Branding - Modern & Professional */}
      <div className="flex items-center space-x-4 mb-16 px-1 group cursor-pointer">
        <div className="relative">
          <div className="absolute inset-0 bg-primary-600 blur-2xl opacity-10 group-hover:opacity-30 transition-all duration-700"></div>
          <div className="relative h-12 w-12 bg-primary-600 border border-primary-500 rounded-2xl flex items-center justify-center shadow-xl rotate-3 group-hover:rotate-0 transition-transform duration-500">
            <Hexagon size={24} className="text-white fill-white/10" strokeWidth={2} />
          </div>
        </div>
        <div>
          <span className="font-extrabold text-2xl tracking-tighter text-slate-900 block leading-none">CAMPUS</span>
          <span className="text-[9px] font-black text-primary-600 tracking-[0.4em] uppercase mt-1.5 block leading-none">Official Registry</span>
        </div>
      </div>

      {/* Navigation - High Contrast Light Mode */}
      <nav className="flex-1 flex flex-col space-y-2 overflow-y-auto pr-2 custom-scrollbar">
        {navItems.map((item) => (
          <NavLink
            key={item.name}
            to={item.path}
            className={({ isActive }: { isActive: boolean }) => `
              group relative flex items-center justify-between px-5 py-4 rounded-2xl transition-all duration-300
              ${isActive 
                ? 'bg-primary-50 text-primary-700 shadow-sm' 
                : 'text-slate-500 hover:text-slate-900 hover:bg-slate-50'}
            `}
          >
            <div className="flex items-center space-x-4 z-10">
              <span className={`transition-all duration-300 ${isActive ? 'text-primary-600 scale-110' : 'group-hover:scale-110 group-hover:text-primary-600'}`}>
                {item.icon}
              </span>
              <span className="font-bold text-sm tracking-tight uppercase tracking-widest text-[11px]">{item.name}</span>
            </div>
            
            {isActive && (
              <div className="h-1.5 w-1.5 rounded-full bg-primary-600 shadow-[0_0_8px_rgba(37,99,235,0.4)]" />
            )}

            {/* Active Highlight Bar */}
            {isActive && (
              <motion.div 
                layoutId="activeNav"
                className="absolute inset-y-3 left-0 w-1 bg-primary-600 rounded-full"
              />
            )}
          </NavLink>
        ))}
      </nav>

      {/* System Status / User Profile */}
      <div className="mt-auto pt-8 border-t border-slate-100 space-y-6">
        <div className="p-5 rounded-3xl bg-slate-50 border border-slate-100 relative group overflow-hidden cursor-pointer shadow-sm">
           <div className="absolute inset-0 bg-primary-600/5 opacity-0 group-hover:opacity-100 transition-opacity" />
           
           <div className="flex items-center space-x-4 relative z-10">
              <div className="relative">
                 <div className="h-10 w-10 rounded-full bg-white border border-slate-200 flex items-center justify-center overflow-hidden shadow-sm">
                    <ShieldCheck size={20} className="text-emerald-600" />
                 </div>
                 <div className="absolute -bottom-0.5 -right-0.5 h-3 w-3 bg-emerald-500 border-2 border-white rounded-full shadow-md" />
              </div>
              <div className="flex-1 min-w-0 font-bold">
                 <span className="text-[11px] font-black text-slate-900 block truncate uppercase tracking-tighter">Root Administrator</span>
                 <span className="text-[9px] text-emerald-600 block uppercase font-black tracking-widest mt-0.5">Secure Session</span>
              </div>
           </div>
        </div>

        <button className="flex items-center justify-center space-x-3 px-6 py-4 w-full text-slate-400 hover:text-rose-600 hover:bg-rose-50 rounded-2xl transition-all group border border-transparent hover:border-rose-100 shadow-sm hover:shadow-md">
          <LogOut size={16} className="group-hover:-translate-x-1 transition-transform" />
          <span className="text-[10px] font-black tracking-[0.2em] uppercase">Terminate Session</span>
        </button>
      </div>
    </motion.aside>
  )
}

export default Sidebar
