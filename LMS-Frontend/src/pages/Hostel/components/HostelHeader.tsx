import { motion } from 'framer-motion'
import { Plus, Building } from 'lucide-react'
import { HOSTEL_TABS } from '../mockData'

interface HostelHeaderProps {
  activeTab: string
  setActiveTab: (tab: string) => void
  setIsDrawerOpen: (open: boolean) => void
}

const HostelHeader = ({ activeTab, setActiveTab, setIsDrawerOpen }: HostelHeaderProps) => {
  return (
    <div className="flex flex-col xl:flex-row xl:items-end justify-between gap-10 border-b border-slate-100 pb-12">
      <div className="max-w-3xl">
        <motion.div 
          initial={{ x: -20, opacity: 0 }}
          animate={{ x: 0, opacity: 1 }}
          className="flex items-center gap-3 mb-6"
        >
          <div className="h-10 w-10 rounded-2xl bg-primary-600/10 flex items-center justify-center text-primary-600 border border-primary-600/20 shadow-sm">
             <Building size={20} />
          </div>
          <h1 className="text-4xl font-black text-slate-900 tracking-tighter uppercase">Hostel Command</h1>
        </motion.div>
        <p className="text-slate-500 text-lg font-medium leading-relaxed">
          Configure residential entities, track occupancy logic, and manage student welfare protocols synchronized with the core database.
        </p>
        
        <div className="flex flex-wrap items-center gap-2 bg-slate-100/80 p-2 rounded-2xl w-max mt-10 border border-slate-200">
          {HOSTEL_TABS.map((tab) => (
            <button
              key={tab.id}
              onClick={() => setActiveTab(tab.id)}
              className={`flex items-center gap-3 px-6 py-3 rounded-[14px] text-[10px] font-black uppercase tracking-widest transition-all duration-300 ${
                activeTab === tab.id 
                ? 'bg-primary-600 text-white shadow-lg shadow-primary-600/20' 
                : 'text-slate-500 hover:text-slate-800 hover:bg-white/50'
              }`}
            >
              {tab.icon}
              {tab.name}
            </button>
          ))}
        </div>
      </div>

      <div className="flex items-center gap-5">
        <button 
          onClick={() => setIsDrawerOpen(true)}
          className="flex items-center gap-4 bg-primary-600 text-white px-10 py-5 rounded-2xl font-black text-xs uppercase tracking-[0.2em] hover:bg-primary-700 transition-all shadow-xl active:scale-95"
        >
          <Plus size={20} strokeWidth={3} />
          Create {activeTab.slice(0, -1)}
        </button>
      </div>
    </div>
  )
}

export default HostelHeader
