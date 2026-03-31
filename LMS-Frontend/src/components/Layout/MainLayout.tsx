import { Outlet } from 'react-router-dom'
import Sidebar from './Sidebar'
import Header from './Header'

const MainLayout = () => {
  return (
    <div className="flex min-h-screen w-full bg-[#09090b] selection:bg-primary-500/20 selection:text-primary-200">
      {/* Background Atmosphere */}
      <div className="ambient-glow-tl" />
      <div className="ambient-glow-br" />
      <div className="fixed inset-0 bg-[#09090b]/40 -z-20" />

      {/* Sidebar - Ultra Glossy */}
      <Sidebar />
      
      {/* Main Content Area */}
      <div className="flex flex-col flex-1 min-h-screen relative overflow-x-hidden">
        <Header />
        
        <main className="flex-1 p-6 lg:p-12 overflow-y-auto custom-scrollbar">
          <div className="max-w-[1800px] mx-auto animate-fade-in relative z-10">
            <Outlet />
          </div>
        </main>
        
        <footer className="px-12 py-8 text-center text-[9px] font-black tracking-[0.6em] text-slate-700/60 uppercase border-t border-white/[0.03] bg-slate-950/40 backdrop-blur-md">
          © 2026 CAMPUS OS CORE • <span className="text-indigo-400/40">INTELLIGENCE ANALYTICS</span> • [NODE_STBL_v2.4]
        </footer>
      </div>
    </div>
  )
}

export default MainLayout
