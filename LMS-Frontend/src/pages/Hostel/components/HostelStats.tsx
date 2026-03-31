import { Hash, ShieldCheck, Zap } from 'lucide-react'

const HostelStats = () => {
  const stats = [
    { label: 'Cluster Connectivity', value: 'OPTIMAL', icon: <Hash size={16} />, color: 'text-emerald-600', bg: 'bg-emerald-50' },
    { label: 'Registry Integrity', value: 'VERIFIED', icon: <ShieldCheck size={16} />, color: 'text-primary-600', bg: 'bg-primary-50' },
    { label: 'Satellite Latency', value: '14MS', icon: <Zap size={16} />, color: 'text-sky-600', bg: 'bg-sky-50' },
  ]

  return (
    <div className="grid grid-cols-1 md:grid-cols-3 gap-10">
      {stats.map((stat, i) => (
        <div key={i} className="bg-white p-7 rounded-[2rem] flex items-center justify-between border border-slate-100 shadow-sm hover:shadow-md transition-all group">
          <div className="flex items-center gap-4">
            <div className={`h-11 w-11 rounded-xl ${stat.bg} flex items-center justify-center ${stat.color} border border-slate-100 group-hover:scale-105 transition-transform`}>
              {stat.icon}
            </div>
            <span className="text-[10px] font-black text-slate-400 uppercase tracking-widest leading-none">{stat.label}</span>
          </div>
          <span className={`text-[11px] font-black uppercase ${stat.color} tracking-tighter`}>{stat.value}</span>
        </div>
      ))}
    </div>
  )
}

export default HostelStats
